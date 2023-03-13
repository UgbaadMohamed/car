import java.io.IOException;

    public class Main {
        void run () throws IOException {
            Menu menu = new Menu();
            menu.readChoiceMenu();
        }
        public static void main(String[] args) throws IOException {
            new Main().run();
        }
}
