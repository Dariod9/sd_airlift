package shared;

import entities.*;
import structs.MemException;
import structs.MemFIFO;

import java.util.ArrayList;

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


    public DepAirport() {

        try {
            this.passengerIDs = new MemFIFO(new Integer[21]);
            this.calleds = new MemFIFO<>(new Integer[21]);
            this.fifoSize = 0;
            this.boarded = 0;
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

        available = true;
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


    public synchronized void checkPassenger() {

        System.out.println("DETAILS:");
        System.out.println(fifoSize);
        System.out.println(boarded);
        System.out.println(flew);
        System.out.println();

        if (((fifoSize == 0 && boarded >= 5) || boarded == 10) || (flew >= 16 && boarded == 21 - flew)) {
            readyTakeOff = true;
//            boarded=0;
            pilotReady = false;
            System.out.println("READY TO TAKE OFF");
            notifyAll();
        }


        int hostessId = ((Hostess) Thread.currentThread()).getHostessID();
        ((Hostess) Thread.currentThread()).setHostessState(HostessStates.checkPassenger);

        while (!pilotReady) {
            try {
                wait();
            } catch (Exception e) {
                System.out.println("Piloto not ready");
            }
        }

        while (fifoSize == 0) {
            try {
                wait();
            } catch (Exception e) {
                System.out.println("OIOIOIOIO");
            }
        }

//        while(fifoSize < 1 && checkingDocs) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        //   System.out.println("GENTE NA FILA");
//        checked=false;

        try {
            chamado = passengerIDs.read();
        } catch (MemException e) {
            e.printStackTrace();
        }


        System.out.println("CHAMOU UM WII");
        fifoSize--;
//        checkingDocs=true;
        notifyAll();
//
        while (!available) {
            try {
                wait();
            } catch (Exception e) {
                System.out.println("Piloto not ready");
            }
        }

        try {
            sleep(500 * (long) Math.random()); //A LER OS DOCS
        } catch (InterruptedException e) {
            System.out.println("NÃO CONSEGUIU LER");
        }

        System.out.println("A LER");
//        flew++;

        checked = true;
        notifyAll();

    }

    public synchronized void showDocuments() {

        int passengerId = ((Passenger) Thread.currentThread()).getPassengerId();
        ((Passenger) Thread.currentThread()).setPassengerState(PassengerStates.inQueue);
        boarded++;
//        flew++;
    }


    public synchronized void waitForNextPassenger() {
        int hostessId = ((Hostess) Thread.currentThread()).getHostessID();
        ((Hostess) Thread.currentThread()).setHostessState(HostessStates.waitForPassenger);

        available = false;
    }

    public synchronized void waitForAllInBoard() {
        int pilotId = ((Pilot) Thread.currentThread()).getPilotID();
        ((Pilot) Thread.currentThread()).setPilotstate(PilotStates.waitingForBoarding);

        System.out.println("\u001B[31m" + " TOU A ESPERA " + "\u001B[0m");
        while (!readyTakeOff) {
//            System.out.println("PRINT DO VALE");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\u001B[32m" + " BORA BORAAAA " + "\u001B[0m");
        flew = flew + boarded;
        boarded = 0;
//        boarded=0;
        readyTakeOff = false;
        notifyAll();


    }
}
