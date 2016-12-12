public class Test
{
    public static void main (String[] args) {
        int num_bod = 2;
        TestWorld pw = new TestWorld(num_bod);
        pw.create();

        // Physics simulation
        for (int i = 0; i < 60; i++) {
            for (int j = 0; j < num_bod; j++)
                System.out.print("Body [" + j + "]: " + pw.d_bodies[j].getPosition() + " | ");
            System.out.println("");

            pw.step();
        }
    }
}
