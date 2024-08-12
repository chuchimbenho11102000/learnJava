public class Main {
    public static void main(String[] args) {
        User chien = new User("chien", 11, "nam","HaNoi");
        User chien2 = new User("ThaiBinh");
        User user = ConvertService.mapData(chien, chien2, User.class);
        System.out.println(user.toString());

        Computer computer = new Computer("MyPC","Dell","Intel CoreI5",32);
        Computer updateSpec = new Computer("I9",64);
        System.out.println(ConvertService.mapData(computer, updateSpec, Computer.class));

    }
}
