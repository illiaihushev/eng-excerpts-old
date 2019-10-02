import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        newFile();
        newFile2();
        newFile601();
        newFile3();
//        preTestForming();
    }


    public static void newFile() throws Exception {
        FileWriter nFile = new FileWriter("course.txt");

        nFile.write("RES MATRIX,16,2\n\n");

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 2; j++) {
                nFile.write("RMULT 4" + j + "1\n");
                nFile.write("extraTime EQU " + i * 10 + "\n");
                nFile.write("SAVEVALUE profit,0\n");

                nFile.write("START 1,NP\n");
                nFile.write("MSAVEVALUE RES,"+ (i + 1) + "," + (j + 1)+ ",X$profit\n");
                nFile.write("CLEAR OFF\n");

            }
//            MEN	STORAGE 4
//            ALLM EQU 50
//            RMULT 401
//            START 1,NP
        }


        nFile.close();
    }

    public static void newFile601() throws Exception {
        FileWriter nFile = new FileWriter("crs601.txt");

        nFile.write("RES MATRIX,16,10\n\n");

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 10; j++) {
                nFile.write("RMULT 6" + j + "1\n");
                nFile.write("extraTime EQU " + i * 10 + "\n");
                nFile.write("SAVEVALUE profit,0\n");

                nFile.write("START 1,NP\n");
                nFile.write("MSAVEVALUE RES,"+ (i + 1) + "," + (j + 1)+ ",X$profit\n");
                nFile.write("CLEAR OFF\n");

            }
//            MEN	STORAGE 4
//            ALLM EQU 50
//            RMULT 401
//            START 1,NP
        }


        nFile.close();
    }

    private static void preTestForming() throws Exception{
        FileWriter nFile = new FileWriter("preTest.txt");

        nFile.write("RES MATRIX,50,1\n\n");

        for (int i = 0; i < 50; i++) {
                nFile.write("RMULT " + (60 + i*2) + "1\n");
                nFile.write("SAVEVALUE profit,0\n");

                nFile.write("START 1,NP\n");
                nFile.write("MSAVEVALUE RES,"+ 1 + "," + (i+1) + ",X$profit\n");
                nFile.write("CLEAR OFF\n");

            }
//            MEN	STORAGE 4
//            ALLM EQU 50
//            RMULT 401
//            START 1,NP
        nFile.close();
    }



    public static void newFile2() throws Exception {
        FileWriter nFile = new FileWriter("courseRectCount2.txt");

        nFile.write("RES MATRIX,16,2\n\n");

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 2; j++) {
                nFile.write("RMULT 4" + j + "1\n");
                nFile.write("extraTime EQU " + i + "\n");
                nFile.write("SAVEVALUE profit,0\n");

                nFile.write("START 1,NP\n");
                nFile.write("MSAVEVALUE RES,"+ (i + 1) + "," + (j + 1)+ ",X$profit\n");
                nFile.write("CLEAR OFF\n");

            }
//            MEN	STORAGE 4
//            ALLM EQU 50
//            RMULT 401
//            START 1,NP
        }


        nFile.close();
    }

    public static void newFile3() throws Exception {
        FileWriter nFile = new FileWriter("courseDumb.txt");

        nFile.write("RES MATRIX,16,2\n\n");

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 2; j++) {
                nFile.write("RMULT 4" + j + "1\n");
                nFile.write("extraTime EQU " + 0 + "\n");
                nFile.write("SAVEVALUE profit,0\n");

                nFile.write("START 1,NP\n");
                nFile.write("MSAVEVALUE RES,"+ (i + 1) + "," + (j + 1)+ ",X$profit\n");
                nFile.write("CLEAR OFF\n");

            }
//            MEN	STORAGE 4
//            ALLM EQU 50
//            RMULT 401
//            START 1,NP
        }


        nFile.close();
    }
}
