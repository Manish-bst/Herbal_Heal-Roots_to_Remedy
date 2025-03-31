import java.io.File;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.Scanner;
 
 public class HealthTips {
     public static void main(String[] args) {
         try {
             File file = new File("health_tips.txt");
             if (!file.exists()) {
                 file.createNewFile();
                 FileWriter writer = new FileWriter(file);
                 writer.write("Health Tips for Various Infections\n\n");
 
                 // Bacterial Infections
                 writer.write("Bacterial Infections:\n");
                 writer.write("1. Wash your hands regularly to prevent the spread of bacteria\n");
                 writer.write("2. Cook food thoroughly to kill harmful bacteria\n");
                 writer.write("3. Stay hydrated and rest to help your body recover\n");
                 writer.write("4. Use antibiotics only when prescribed by a doctor\n");
                 writer.write("5. Avoid sharing personal items like towels\n");
                 writer.write("6. Clean wounds with antiseptic to prevent infection\n");
                 writer.write("7. Keep your environment clean to minimize bacteria\n");
                 writer.write("8. Avoid smoking, which can weaken your immune system\n");
                 writer.write("9. Practice safe food handling and storage\n");
                 writer.write("10. Avoid contact with infected individuals\n\n");
 
                 // Viral Infections
                 writer.write("Viral Infections:\n");
                 writer.write("1. Get vaccinated to prevent viral infections\n");
                 writer.write("2. Avoid close contact with infected individuals\n");
                 writer.write("3. Maintain good hygiene practices\n");
                 writer.write("4. Rest and hydrate to help your body recover\n");
                 writer.write("5. Wear a mask to prevent spreading the virus\n");
                 writer.write("6. Avoid touching your face to reduce risk of infection\n");
                 writer.write("7. Disinfect frequently touched surfaces regularly\n");
                 writer.write("8. Limit outdoor activities during an outbreak\n");
                 writer.write("9. Eat a healthy diet to boost your immune system\n");
                 writer.write("10. Seek medical attention if symptoms worsen\n\n");
 
                 // Fungal Infections
                 writer.write("Fungal Infections:\n");
                 writer.write("1. Keep your skin dry and clean\n");
                 writer.write("2. Use antifungal powders or creams as needed\n");
                 writer.write("3. Wear breathable clothing to reduce moisture\n");
                 writer.write("4. Avoid tight-fitting shoes that trap moisture\n");
                 writer.write("5. Change socks frequently, especially if they become damp\n");
                 writer.write("6. Do not share personal items like shoes or towels\n");
                 writer.write("7. Keep nails trimmed and clean\n");
                 writer.write("8. Use antifungal treatments as directed by a doctor\n");
                 writer.write("9. Disinfect household surfaces and bathrooms\n");
                 writer.write("10. Avoid walking barefoot in public places like pools or gyms\n\n");
 
                 // Parasite Infections
                 writer.write("Parasite Infections:\n");
                 writer.write("1. Wash hands thoroughly before eating or preparing food\n");
                 writer.write("2. Drink clean, treated water to prevent waterborne parasites\n");
                 writer.write("3. Avoid consuming undercooked meat or seafood\n");
                 writer.write("4. Use insect repellent to protect against mosquito bites\n");
                 writer.write("5. Treat pets for fleas, ticks, and worms regularly\n");
                 writer.write("6. Maintain good hygiene in outdoor activities like camping\n");
                 writer.write("7. Always wash fruits and vegetables thoroughly before eating\n");
                 writer.write("8. Avoid walking barefoot in areas where parasites are common\n");
                 writer.write("9. Seek medical treatment if symptoms of parasitic infections appear\n");
                 writer.write("10. Keep your environment clean and free of pests\n\n");
 
                 // Chronic Conditions
                 writer.write("Chronic Conditions:\n");
                 writer.write("1. Follow a balanced diet rich in fruits, vegetables, and whole grains\n");
                 writer.write("2. Exercise regularly to improve heart and lung health\n");
                 writer.write("3. Stay hydrated and drink plenty of water throughout the day\n");
                 writer.write("4. Manage stress through relaxation techniques like meditation\n");
                 writer.write("5. Take medications as prescribed and regularly monitor symptoms\n");
                 writer.write("6. Get enough sleep to help your body repair and rejuvenate\n");
                 writer.write("7. Avoid smoking, excessive alcohol, and other harmful substances\n");
                 writer.write("8. Keep track of your health with regular check-ups and screenings\n");
                 writer.write("9. Stay socially active to maintain mental and emotional health\n");
                 writer.write("10. Follow your doctor’s advice for managing your chronic condition\n\n");
 
                 // Allergic Reactions
                 writer.write("Allergic Reactions:\n");
                 writer.write("1. Identify and avoid triggers (e.g., pollen, dust, pet dander)\n");
                 writer.write("2. Carry an epinephrine auto-injector if prescribed by your doctor\n");
                 writer.write("3. Take antihistamines to relieve allergy symptoms\n");
                 writer.write("4. Keep windows closed during pollen season to reduce exposure\n");
                 writer.write("5. Wash hands and change clothes after being in contact with allergens\n");
                 writer.write("6. Use air purifiers to reduce indoor allergens\n");
                 writer.write("7. Avoid smoking or secondhand smoke, which can worsen allergies\n");
                 writer.write("8. Clean carpets and bedding regularly to reduce dust mites\n");
                 writer.write("9. Keep pets clean and bathe them regularly to reduce allergens\n");
                 writer.write("10. Consult with an allergist for personalized treatment\n\n");
 
                 // Respiratory Infections
                 writer.write("Respiratory Infections:\n");
                 writer.write("1. Avoid close contact with infected individuals\n");
                 writer.write("2. Wash your hands frequently to reduce the spread of germs\n");
                 writer.write("3. Wear a mask if you are in public during a respiratory outbreak\n");
                 writer.write("4. Use a humidifier to help with dry air and ease breathing\n");
                 writer.write("5. Rest and drink plenty of fluids to aid recovery\n");
                 writer.write("6. Avoid smoking or exposure to secondhand smoke\n");
                 writer.write("7. Seek medical attention if symptoms worsen or become severe\n");
                 writer.write("8. Use cough drops or lozenges to soothe a sore throat\n");
                 writer.write("9. Avoid cold air and extreme temperature changes\n");
                 writer.write("10. Keep your living space clean and disinfect commonly touched surfaces\n\n");
 
                 // Skin Conditions
                 writer.write("Skin Conditions:\n");
                 writer.write("1. Keep your skin clean and moisturized\n");
                 writer.write("2. Avoid harsh soaps and hot water that can dry out your skin\n");
                 writer.write("3. Use sunscreen to protect your skin from harmful UV rays\n");
                 writer.write("4. Avoid scratching or picking at rashes or wounds\n");
                 writer.write("5. Use over-the-counter creams or ointments for common skin conditions\n");
                 writer.write("6. Stay hydrated by drinking plenty of water\n");
                 writer.write("7. Wear loose clothing to prevent irritation on sensitive skin\n");
                 writer.write("8. Seek medical advice for persistent or severe skin conditions\n");
                 writer.write("9. Practice good hygiene and wash your hands regularly\n");
                 writer.write("10. Avoid exposure to allergens or irritants that may worsen skin conditions\n\n");
 
                 // Autoimmune Diseases
                 writer.write("Autoimmune Diseases:\n");
                 writer.write("1. Follow a balanced diet to support your immune system\n");
                 writer.write("2. Get regular exercise to maintain strength and flexibility\n");
                 writer.write("3. Manage stress with relaxation techniques such as meditation\n");
                 writer.write("4. Take prescribed medications as directed by your healthcare provider\n");
                 writer.write("5. Keep track of your symptoms and report changes to your doctor\n");
                 writer.write("6. Get enough sleep to allow your body to recover\n");
                 writer.write("7. Avoid smoking, as it can aggravate autoimmune conditions\n");
                 writer.write("8. Join support groups to connect with others who understand your condition\n");
                 writer.write("9. Stay hydrated to help maintain overall health\n");
                 writer.write("10. Regularly monitor and manage any other health conditions that may impact your immune system\n\n");
 
                 writer.close();
                 System.out.println("Health tips written to health_tips.txt");
             } else {
                 System.out.println("File already exists. Reading health tips from the file...");
             }
 
             // Asking user to select an agent
             Scanner scanner = new Scanner(System.in);
             System.out.println("Select an infection agent to view health tips:");
             System.out.println("1. Bacterial Infections");
             System.out.println("2. Viral Infections");
             System.out.println("3. Fungal Infections");
             System.out.println("4. Parasite Infections");
             System.out.println("5. Chronic Conditions");
             System.out.println("6. Allergic Reactions");
             System.out.println("7. Respiratory Infections");
             System.out.println("8. Skin Conditions");
             System.out.println("9. Autoimmune Diseases");
             System.out.print("Enter the number of your choice: ");
             int choice = scanner.nextInt();
             
             switch (choice) {
                 case 1:
                     printHealthTips("Bacterial Infections");
                     break;
                 case 2:
                     printHealthTips("Viral Infections");
                     break;
                 case 3:
                     printHealthTips("Fungal Infections");
                     break;
                 case 4:
                     printHealthTips("Parasite Infections");
                     break;
                 case 5:
                     printHealthTips("Chronic Conditions");
                     break;
                 case 6:
                     printHealthTips("Allergic Reactions");
                     break;
                 case 7:
                     printHealthTips("Respiratory Infections");
                     break;
                 case 8:
                     printHealthTips("Skin Conditions");
                     break;
                 case 9:
                     printHealthTips("Autoimmune Diseases");
                     break;
                 default:
                     System.out.println("Invalid choice.");
             }
 
         } catch (IOException e) {
             System.out.println("An error occurred while writing to or reading from the file.");
             e.printStackTrace();
         }
     }
 
     private static void printHealthTips(String agent) {
         try (Scanner fileScanner = new Scanner(new File("health_tips.txt"))) {
             boolean isAgentFound = false;
 
             while (fileScanner.hasNextLine()) {
                 String line = fileScanner.nextLine();
                 if (line.contains(agent)) {
                     isAgentFound = true;
                     System.out.println(line); // Print the agent header
                 } else if (isAgentFound && line.isEmpty()) {
                     // Stop printing once we reach the next agent's header
                     break;
                 } else if (isAgentFound) {
                     System.out.println(line); // Print health tips for the selected agent
                 }
             }
 
             if (!isAgentFound) {
                 System.out.println("Health tips for " + agent + " not found.");
             }
 
         } catch (IOException e) {
             System.out.println("An error occurred while reading the health tips file.");
             e.printStackTrace();
         }
     }
 }
