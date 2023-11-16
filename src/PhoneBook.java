
import java.util.*;



public class PhoneBook {
    public static String name;
    public static HashMap<String, ArrayList<String>> phoneBook = new HashMap<>();
    public static ArrayList<String> nameUser = new ArrayList<>(phoneBook.keySet());
    public static ArrayList<ArrayList<String>> phone = new ArrayList<>(phoneBook.values());



    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите свое имя: ");
        name = scan.next();

        menuPhoneBook();

    }

    public static void menuPhoneBook() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Здравствуйте " + name.toUpperCase() + "\n " +
                "Это телефонный справочник версии 1.0\n" +
                "\n**************************************\n \n" +
                "Выберите что вы хотите сделать: \n" +
                "1 - Вывести весь телефонный справочник. \n" +
                "2 - Добавить контакт в телефонный справочник. \n" +
                "3 - Найти телефоны по имени. \n" +
                "***************************************");

           int opNumber;
           while (true){
               try {
                   opNumber = Integer.parseInt(scan.next());
                   break;
               }catch (Exception e){
                   System.out.println("Вы ввели не число!!!");
                   returnMenu();
               }
           }

            if (opNumber == 1) {
                printPhoneBook();
            } else if (opNumber == 2) {
                addNewContact();
            } else if (opNumber == 3) {
                findPhoneNumber();
            } else {
                System.out.println("Операции с таким номером не существует!");
                returnMenu();
            }

        }

    public static void returnMenu() {
        System.out.println("--------------------------------\nДля возврата в главное меню нажмите 0!");
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();

        if (number == 0) {
            menuPhoneBook();
        } else {

            returnMenu();
        }

    }

    public static void printPhoneBook() {
        if (phoneBook.isEmpty()) {
            System.out.println("Ваш телефонный справочник пуст!");
        }
        else
        {
        LinkedHashMap<String,ArrayList<String>> sortedByValue = new LinkedHashMap<>();
        ArrayList<ArrayList<String>> listMap = new ArrayList<>();
        for(Map.Entry<String,ArrayList<String>> entry:phoneBook.entrySet()){
            listMap.add(entry.getValue());
            }
        Collections.sort(listMap, new Comparator<ArrayList<String>>() {
            public int compare(ArrayList<String> s1,ArrayList<String> s2){
                return s2.size() - s1.size();
            }


        });
        for (ArrayList<String> list:listMap){
            for (Map.Entry<String,ArrayList<String>> entry:phoneBook.entrySet()){
                if(entry.getValue().equals(list)){
                    sortedByValue.put(entry.getKey(),list);
                }
            }
        }
            for (String key:sortedByValue.keySet()){
                System.out.println("Имя: " + key +": номер(а) телефона(ов) - " + sortedByValue.get(key).toString());
            }
        }

        returnMenu();

    }
    public static void addNewContact() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите имя контакта: ");
        String nameContact = scan.next().toLowerCase();
        System.out.println("Введите номер телефона: ");
        String numberPhone = scan.next().toLowerCase();
        boolean isTrue = true;
        if (phoneBook.isEmpty()) {
            ArrayList<String> phoneList = new ArrayList<>();
            phoneList.add(numberPhone);
            phoneBook.put(nameContact, phoneList);
            nameUser.add(nameContact);
            phone.add(phoneList);
            isTrue=false;
        }
        else {
            for (int i = 0; i < nameUser.size(); i++) {
                if (nameContact.equals(nameUser.get(i))) {
                    ArrayList<String> phoneList  = new ArrayList<>();
                    phoneList = phone.get(i);
                    phoneList.add(numberPhone);
                    phoneBook.put(nameContact,phoneList);
                    System.out.println(phoneBook);
                    isTrue = false;
                }

            }
        }

            if(isTrue){
                ArrayList<String> phoneList = new ArrayList<>();
                phoneList.add(numberPhone);
                phoneBook.put(nameContact, phoneList);
                nameUser.add(nameContact);
                phone.add(phoneList);
                System.out.println(phoneBook);
                returnMenu();
            }

        else {
            returnMenu();
        }

    }
    public static void findPhoneNumber(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите имя : ");
        String findName = scan.next().toLowerCase();
        boolean find = false;
        for (String key:phoneBook.keySet()){
            if(findName.contains(key)){
                System.out.println("Имя: " + key + " номер(а) телефона(ов) - " + phoneBook.get(key));
                find = true;
            }
        }
        if (!find){
            System.out.println("Такого контакта нет в вашем телефонном справочнике!!!");
            returnMenu();
        }
        else {
            returnMenu();
        }

    }

}
