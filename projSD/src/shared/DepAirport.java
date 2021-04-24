package shared;

import entities.*;
import structs.MemException;
import structs.MemFIFO;

import static java.lang.Thread.sleep;

public class DepAirport {

    private MemFIFO<Integer> passengerIDs;
    private MemFIFO<Integer> calleds;
    private int chamado = -1;
    private boolean pilotReady = false;
    private boolean available = false;
    private boolean checked = false;
    private int fifoSize;
    private int boarded;
    private int flew = 0;
    private boolean readyTakeOff = false;
    private boolean planeReady = false;
    private Repository repos;


    public DepAirport(Repository repos) {

        try {
            this.passengerIDs = new MemFIFO(new Integer[21]);
            this.calleds = new MemFIFO<>(new Integer[21]);
            this.fifoSize = 0;
            this.boarded = 0;
            this.repos=repos;
        } catch (MemException e) {
            e.printStackTrace();
        }
    }


//    public synchronized void travelToAirport() {
//        int passengerId = ((Passenger) Thread.currentThread ()).getPassengerId();
//        ((Passenger) Thread.currentThread ()).setPassengerState (PassengerStates.inQueue);
//
//
//    }


    public void setFlew(int flew) {
        this.flew = flew;
    }

    public int getFlew() {
        return this.flew;
    }

    public boolean isPilotReady() {
        return pilotReady;
    }

    public synchronized void setPilotReady(boolean pilotReady) {
        this.pilotReady = pilotReady;
        notifyAll();
    }


    public synchronized void waitInQueue() {
        int passengerId = ((Passenger) Thread.currentThread()).getPassengerId();
        ((Passenger) Thread.currentThread()).setPassengerState(PassengerStates.inQueue);
        repos.setPassengerState(passengerId,((Passenger) Thread.currentThread()).getPassengerState());

        try {
            passengerIDs.write(passengerId);
            fifoSize++;
            try {
                sleep(2000 * (long) Math.random());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Passageiro " + passengerId + " entrou na fila");
        } catch (MemException e) {
            e.printStackTrace();
        }

        notifyAll();

        while (chamado != passengerId) {
            try {
                wait();
            } catch (Exception e) {
                System.out.println("Erro na waitInQueue: " + e);
            }
        }

        //available = true;
//        ((Passenger) Thread.currentThread()).setavailable(true);
//        System.out.println("Passageiro "+passengerId+" mostrou os docs");
        //checkingDocs=true;

        notifyAll();

        while (!checked) {
            try {
                wait();
            } catch (Exception e) {
                System.out.println("Erro na waitInQueue: " + e);
            }
        }

        checked = false;

    }

    public synchronized void waitForNextPassenger() {
        int hostessId = ((Hostess) Thread.currentThread()).getHostessID();
        ((Hostess) Thread.currentThread()).setHostessState(HostessStates.waitForPassenger);
        repos.setHostessState(((Hostess) Thread.currentThread()).getHostessState());
//        available = true;
//        notifyAll();


        while (fifoSize == 0 ) {
            try {
                wait();
            } catch (Exception e) {
                System.out.println("Couldnt wait for next Passenger");
            }
        }

    }

    public synchronized void checkDocuments() {

        int hostessId = ((Hostess) Thread.currentThread()).getHostessID();

        ((Hostess) Thread.currentThread()).setHostessState(HostessStates.checkPassenger);
        //repos.setHostessState(((Hostess) Thread.currentThread()).getHostessState());


//        while (!available) {
//            try {
//                wait();
//            } catch (Exception e) {
//                System.out.println("Couldnt wait for next Passenger");
//            }
//        }
//
//        available=false;

        try {
            if (boarded < 10) {
                chamado = passengerIDs.read();
                fifoSize--;
            }
        } catch (MemException e) {
            e.printStackTrace();
        }
        //        checkingDocs=true;
        try {
            sleep(500 * (long) Math.random()); //A LER OS DOCS
        } catch (InterruptedException e) {
            System.out.println("NÃO CONSEGUIU LER");
        }
        System.out.println("A LER");
//        flew++;
        repos.setHostessState(((Hostess) Thread.currentThread()).getHostessState(), chamado);

        checked = true;
        boarded++;
        notifyAll();

    }


    public synchronized void informPlaneReadyToTakeOff() {
        int hostessId = ((Hostess) Thread.currentThread()).getHostessID();

//        while (!planeReady) {
//            try {
//                wait();
//            } catch (Exception e) {
//                System.out.println("Plane not ready");
//            }
//        }
//
//        //if (((fifoSize == 0 && boarded >= 5 && boarded <= 10) || boarded == 10) || (flew >= 16 && boarded == 21 - flew)) {
//        if(boarded==10){
        if (flew >= 16) {
            if (boarded == 21 - flew){
                readyTakeOff = true;
//            boarded=0;
                pilotReady = false;
                System.out.println("READY TO TAKE OFF");
                notifyAll();

                ((Hostess) Thread.currentThread()).setHostessState(HostessStates.readyToFly);
                repos.setHostessState(((Hostess) Thread.currentThread()).getHostessState());


            }
        }
        else {
                if (boarded == 10) {
                    readyTakeOff = true;
//            boarded=0;
                    pilotReady = false;
                    System.out.println("READY TO TAKE OFF");
                    notifyAll();
                    ((Hostess) Thread.currentThread()).setHostessState(HostessStates.readyToFly);
                    repos.setHostessState(((Hostess) Thread.currentThread()).getHostessState());
                } else {
                    if (fifoSize == 0 && boarded >= 5) {
                        readyTakeOff = true;
//            boarded=0;
                        pilotReady = false;
                        System.out.println("READY TO TAKE OFF");
                        notifyAll();
                        ((Hostess) Thread.currentThread()).setHostessState(HostessStates.readyToFly);
                        repos.setHostessState(((Hostess) Thread.currentThread()).getHostessState());
                    }
                }


            }
    }

    public synchronized void waitForNextFlight() {
        int hostessId = ((Hostess) Thread.currentThread()).getHostessID();
        ((Hostess) Thread.currentThread()).setHostessState(HostessStates.waitForFlight);
        repos.setHostessState(((Hostess) Thread.currentThread()).getHostessState());

        while (readyTakeOff) {
            try {
                wait();
            } catch (Exception e) {
                System.out.println("Piloto not ready");
            }
        }

    }

//    public synchronized void checkPassenger() {
//
//        int hostessId = ((Hostess) Thread.currentThread()).getHostessID();
//        ((Hostess) Thread.currentThread()).setHostessState(HostessStates.checkPassenger);
//        repos.setHostessState(((Hostess) Thread.currentThread()).getHostessState());
//
//        while (!pilotReady) {
//            try {
//                wait();
//            } catch (Exception e) {
//                System.out.println("Piloto not ready");
//            }
//        }
//
//        while (fifoSize == 0) {
//            try {
//                wait();
//            } catch (Exception e) {
//                System.out.println("OIOIOIOIO");
//            }
//        }
//
////        while(fifoSize < 1 && checkingDocs) {
////            try {
////                wait();
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////        }
//
//        //   System.out.println("GENTE NA FILA");
////        checked=false;
//
//
////
////        while (!available) {
////            try {
////                wait();
////            } catch (Exception e) {
////                System.out.println("Piloto not ready");
////            }
////        }
//
//
//    }

    public synchronized void showDocuments() {
        System.out.println("DETAILS:");
        System.out.println(fifoSize);
        System.out.println(boarded);
        System.out.println(flew);
        System.out.println();

        int passengerId = ((Passenger) Thread.currentThread()).getPassengerId();
//        boarded++;
//        flew++;

//        try {
//            sleep(200 * (long) Math.random()); //A LER OS DOCS
//        } catch (InterruptedException e) {
//            System.out.println("NÃO CONSEGUIU MOSTRAR OS DOCS");
//        }
    }

    public synchronized void informPlaneReadyForBoarding() {
        int pilotId = ((Pilot) Thread.currentThread()).getPilotID();
        ((Pilot) Thread.currentThread()).setPilotstate(PilotStates.readyForBoarding);
        repos.setPilotState(((Pilot) Thread.currentThread()).getPilotstate());

        pilotReady = true;
        notifyAll();
        System.out.println("Piloto diz que tá ready");
    }


    public synchronized void waitForAllInBoard() {
        int pilotId = ((Pilot) Thread.currentThread()).getPilotID();
        ((Pilot) Thread.currentThread()).setPilotstate(PilotStates.waitingForBoarding);
        repos.setPilotState(((Pilot) Thread.currentThread()).getPilotstate());

        System.out.println("\u001B[31m" + " TOU A ESPERA " + "\u001B[0m");
        System.out.println("ESTAO NA FILA " + fifoSize);
        while (!readyTakeOff) {
            System.out.println("PRINT DO VALE");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void flyToDestinationPoint() {
        repos.addFlightInfo(boarded);
        int pilotId = ((Pilot) Thread.currentThread()).getPilotID();
        ((Pilot) Thread.currentThread()).setPilotstate(PilotStates.flyingForward);
        repos.setPilotState(((Pilot) Thread.currentThread()).getPilotstate());

        System.out.println("\u001B[32m" + " BORA BORAAAA " + "\u001B[0m");
        System.out.println("DETAILS:");
        System.out.println(fifoSize);
        System.out.println(boarded);
        flew = flew + boarded;
        System.out.println(flew);
        System.out.println();

        boarded = 0;
        readyTakeOff = false;
        ((Pilot) Thread.currentThread()).fly();
        notifyAll();
    }


}
