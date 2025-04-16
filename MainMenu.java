import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Run the login code
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.main(new String[0]);

        // Display the main menu
        while (true) {
            System.out.println("Main Menu");
            System.out.println("1. Symptom Checker");
            System.out.println("2. Disease Information");
            System.out.println("3. Herbal Remedy Information");
            System.out.println("4. Health Tips");
            System.out.println("5. Personal Health Care Plan");
            System.out.println("6. User Review System");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    SymptomChecker symptomChecker = new SymptomChecker();
                    symptomChecker.main(new String[0]);
                    break;
                case 2:
                    DiseaseInformation diseaseInformation = new DiseaseInformation();
                    diseaseInformation.main(new String[0]);
                    break;
                case 3:
                    HerbalRemedyInformation herbalRemedyInformation = new HerbalRemedyInformation();
                    herbalRemedyInformation.main(new String[0]);
                    break;
                case 4:
                    HealthTips healthTips = new HealthTips();
                    healthTips.main(new String[0]);
                    break;
                case 5:
                    PersonalHealthCarePlan personalHealthCarePlan = new PersonalHealthCarePlan();
                    personalHealthCarePlan.main(new String[0]);
                    break;
                case 6:
                    UserReviewSystem userReviewSystem = new UserReviewSystem();
                    userReviewSystem.main(new String[0]);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}

