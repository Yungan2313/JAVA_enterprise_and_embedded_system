package hw1;

import java.util.Scanner;

public class Computer_Simulator {
    private static final Scanner input = new Scanner(System.in);
    private static final int TOTAL_MEMORY = 100;

    private static final int READ = 10;
    private static final int WRITE = 11;
    private static final int LOAD = 20;
    private static final int STORE = 21;
    private static final int ADD = 30;
    private static final int SUBTRACT = 31;
    private static final int DIVIDE = 32;
    private static final int MULTIPLY = 33;
    private static final int BRANCH = 40;
    private static final int BRANCHNEG = 41;
    private static final int BRANCHZERO = 42;
    private static final int HALT = 43;
    // -------------------------------------------code
    private int accumulator;
    private int operationCode;
    private int operand;
    private int instructionCount;
    private int instructionRegister;
    private static int[] memory = new int[TOTAL_MEMORY];

    // --------------------------------------------test
    public static void main(String[] args) {
        Computer_Simulator test = new Computer_Simulator();
        test.Begin();
        test.Computer_Simulator_run();
        test.print_memory();
    }

    // ---------------------------------------------function
    public void Begin() {
        System.out.printf("*** Welcome to Simpletron! ***\n"
                + "*** Please enter your program one instruction ***\n"
                + "*** (or data word) at a time. I will display ***\n"
                + "*** the location number and a question mark (?). ***\n"
                + "*** You then type the word for that location. ***\n"
                + "*** Type -99999 to stop entering your program. ***\n\n");

        int instruction = 0;
        instructionCount = 0;
        // ---------------------------------------輸入指令
        System.out.printf("%02d ? ", instructionCount);
        instruction = input.nextInt();
        while (instruction != -99999) {
            if (instruction / 1000 >= 0 && instruction / 1000 < 10) {
                memory[instructionCount] = instruction;
                instructionCount++;
            } else
                System.out.printf("*** Errorl: Invalid input!\n"
                        + "Please enter again ***\n");

            System.out.printf("%02d ? ", instructionCount);
            instruction = input.nextInt();
        }
    }

    public void Computer_Simulator_run() {
        int temp;
        System.out.printf("*** Program loading completed ***\n");
        System.out.printf("*** Program execution begins ***\n");
        instructionCount = 0;
        instructionRegister = 0;
        while (instructionRegister != HALT * 100) {
            // Computer_Simulator test = new Computer_Simulator();
            // test.print_memory();
            instructionRegister = memory[instructionCount];
            instructionCount++;
            operationCode = instructionRegister / 100;
            operand = instructionRegister % 100;
            switch (operationCode) {
                case READ:
                    System.out.printf("Enter an integer\n");
                    temp = input.nextInt();
                    if (overflow_check(temp)) {
                        return;
                    }
                    memory[operand] = temp;
                    break;
                case WRITE:
                    System.out.println(memory[operand]);
                    break;
                case LOAD:
                    accumulator = memory[operand];
                    break;
                case STORE:
                    memory[operand] = accumulator;
                    break;
                case ADD:
                    accumulator += memory[operand];
                    if (overflow_check(accumulator)) {
                        return;
                    }
                    break;
                case SUBTRACT:
                    // System.out.println("okkkkkkkkkk");
                    // System.out.println(accumulator);
                    // System.out.println(memory[operand]);
                    accumulator -= memory[operand];
                    if (overflow_check(accumulator)) {
                        return;
                    }
                    break;
                case DIVIDE:
                    if (memory[operand] == 0) {
                        System.out.printf("*** Attempt to divide by zero ***\n");
                        return;
                    }
                    accumulator /= memory[operand];
                    if (overflow_check(accumulator)) {
                        return;
                    }
                    break;
                case MULTIPLY:
                    accumulator *= memory[operand];
                    if (overflow_check(accumulator)) {
                        return;
                    }
                    break;
                case BRANCH:
                    instructionCount = operand;
                    break;

                case BRANCHNEG:
                    if (accumulator < 0) {
                        instructionCount = operand;
                    }
                    break;
                case BRANCHZERO:
                    if (accumulator == 0) {
                        instructionCount = operand;
                    }
                    break;
                case HALT:
                    System.out.printf("*** Simpletron execution abnormally terminated ***\n");
                    break;
                default:
                    System.out.printf("*** Error:Invalid operation code ***\n");
                    break;
            }
        }
    }

    public void print_memory() {
        char acc = '+';
        System.out.println("\t\t\tREGISTERS:");
        if (accumulator >= 0) {
            acc = '+';
        } else {
            accumulator *= -1;
            acc = '-';
        }
        System.out.printf("\t\taccumulator         %s%04d%n", acc, accumulator);
        System.out.printf("\t\tinstructionCount       %02d%n", instructionCount);
        System.out.printf("\t\tinstructionRegister +%04d%n", instructionRegister);
        System.out.printf("\t\toperationCode          %02d%n", operationCode);
        System.out.printf("\t\toperand                %02d%n", operand);

        System.out.println("\t\t\tMEMORY:");
        for (int i = 0; i < 10; i++) {
            System.out.printf("%6d", i);
        }
        for (int i = 0; i < TOTAL_MEMORY; i++) {
            if (i % 10 == 0) {
                System.out.printf("\n%2d ", i);
            }
            if (memory[i] >= 0) {
                acc = '+';
            } else {
                acc = '-';
                memory[i] *= -1;
            }
            System.out.printf("%s%04d ", acc, memory[i]);
        }
    }

    public boolean overflow_check(int input) {
        if (input < -9999 || input > 9999) {
            System.out.printf("*** Error:Accumulator overflow ***\n");
            return true;
        }
        return false;
    }
}