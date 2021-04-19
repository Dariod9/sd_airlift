package entities;

public final class PilotStates
    {
        /**
         *   The barber is resting while waiting for a customer.
         */

        public static final int atTransferGate = 0;

        /**
         *   The barber is cutting some customer hair.
         */

        public static final int readyForBoarding = 1;

        /**
         *   It can not be instantiated.
         */

        public static final int waitingForBoarding = 2;

        /**
         *   It can not be instantiated.
         */

        public static final int flyingForward = 3;

        /**
         *   It can not be instantiated.
         */

        public static final int deBoarding = 4;

        /**
         *   It can not be instantiated.
         */

        public static final int flyingBack = 5;

        /**
         *   It can not be instantiated.
         */


        private PilotStates()
        { }
    }