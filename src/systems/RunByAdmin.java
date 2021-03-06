package systems;

import account.UserManager;
import login.Login;
import model.Bill;
import model.Room;
import model.Service;
import modelmanager.BillManager;
import modelmanager.OrderServiceManager;
import modelmanager.RoomManager;
import modelmanager.ServiceManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RunByAdmin {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public static final int ZERO = 0;
    public static final int FIRST = 1;
    public static final int SECOND = 2;
    public static final int THIRD = 3;
    public static final int FOURTH = 4;
    public static final int FIVE = 5;
    public static final int SIXTH = 6;
    public static final int SEVENTH = 7;
    public static final int EIGHTH = 8;
    public static final int NINTH = 9;
    private final Scanner scan = new Scanner(System.in);
    private final BillManager billManager = new BillManager();
    private final RoomManager roomManager = new RoomManager();
    private final UserManager userManager = new UserManager();
    private final ServiceManager serviceManager = new ServiceManager();
    private final OrderServiceManager orderServiceManager = new OrderServiceManager();

    public RunByAdmin() {
    }

    public void menuOfAdmin() {
        try {
            do {
                int choice = choiceOfAdmin();
                if (choice < 0 || choice > 9) {
                    System.out.println();
                    System.out.println(ANSI_RED+"?????? L???a ch???n kh??ng t???n t???i, m???i b???n nh???p l???i !!!"+ANSI_RESET);
                    System.out.println("--------------------");
                }
                switch (choice) {
                    case FIRST:
                        menuRoomManager();
                        break;
                    case SECOND:
                        menuBillManager();
                        break;
                    case THIRD:
                        menuServiceManager();
                        break;
                    case FOURTH:
                        menuOrderServiceManager();
                        break;
                    case FIVE:
                        System.out.println("Nh???p v??o ph??ng:");
                        scan.nextLine();
                        String roomName = scan.nextLine();
                        System.out.println("Nh???p ng??y Check-out(dd-mm-yyyy):");
                        String checkIn = scan.nextLine();
                        LocalDate checkInDate = LocalDate.parse(checkIn, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        checkOut(roomName, checkInDate);
                        break;
                    case SIXTH:
                        userManager.displayUserList();
                        break;
                    case SEVENTH:
                        System.out.println("Nh???p t??i kho???n mu???n x??a:");
                        String accountDelete = scan.nextLine();
                        scan.nextLine();
                        userManager.deleteByName(accountDelete);
                        break;
                    case EIGHTH:
                        System.out.println("Nh???p v??o th??ng:");
                        int month = scan.nextInt();
                        System.out.println("Nh???p v??o n??m:");
                        int year = scan.nextInt();
                        if (month < 1 || month > 12 || year < 2015) {
                            System.out.println(ANSI_RED+"??? Nh???p sai d??? li???u, m???i nh???p l???i !!!"+ANSI_RESET);
                            System.out.println("--------------------");
                        } else {
                            System.out.println("T???ng doanh thu " + month + "/" + year + ": " + getTotalInAMonth(month, year));
                        }
                        break;
                    case NINTH:
                        userManager.displayAll();
                        break;
                    case ZERO:
                        exitOfAdmin();
                        break;
                }
            } while (true);
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println();
            System.out.println(ANSI_RED+"??? B???n nh???p sai d??? li???u, m???i nh???p l???i !!!"+ANSI_RESET);
            System.out.println("--------------------");
            System.out.println();
            menuOfAdmin();
        }
    }

    private double getTotalInAMonth(int month, int year) {
        return (billManager.getTotalBillInAMonth(month, year) + orderServiceManager.getTotalInAMonth(month, year));
    }

    private double getTotal(String roomName, LocalDate checkInDate) {
        return (billManager.getBillCheckOut(roomName, checkInDate) + orderServiceManager.getTotalCheckOut(roomName, checkInDate));
    }

    private void checkOut(String roomName, LocalDate checkInDate) {
        billManager.displayBillCheckOut(roomName, checkInDate);
        System.out.println();
        System.out.println("??? ??? ??? ??? ??? ??? ??? ??? ??? ??? ??? ??? ??? ??? ??? ??? ");
        System.out.println();
        orderServiceManager.displayByRoomName(roomName, checkInDate);
        System.out.println();
        System.out.println("??? T???ng s??? ti???n ph???i thanh to??n: " + getTotal(roomName, checkInDate));
        System.out.println("--------------------");
    }


    private int choiceOfAdmin() {
        int choice = 0;
        while (true) {
            try {
                System.out.println(ANSI_CYAN + "???===================================================???");
                System.out.println("???         ??? ??? ??? ??? ??? H??? TH???NG ADMIN ??? ??? ??? ??? ???        ???");
                System.out.println("???===================================================???");
                System.out.println("???>[1]. Qu???n l?? ph??ng                                ???");
                System.out.println("???>[2]. Qu???n l?? h??a ????n                              ???");
                System.out.println("???>[3]. Qu???n l?? d???ch v???                              ???");
                System.out.println("???>[4]. Kh??ch h??ng ?????t d???ch v???                       ???");
                System.out.println("???>[5]. Check-Out                                    ???");
                System.out.println("???>[6]. Hi???n th??? th??ng tin USER                      ???");
                System.out.println("???>[7]. X??a USER                                     ???");
                System.out.println("???>[8]. T??nh t???ng doanh thu theo th??ng               ???");
                System.out.println("???>[9]. Hi???n th??? t???t c??? th??ng tin c???a USER           ???");
                System.out.println("???>[0]. ????ng xu???t                                    ???");
                System.out.println("???===================================================???" + ANSI_RESET);
                System.out.println("[\uD83D\uDD11] Nh???p l???a ch???n:");
                choice = scan.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "?????? Vui l??ng ch??? nh???p s??? ????? ch???n ch???c n??ng" + ANSI_RESET);
                scan.nextLine();
            }
        }
        return choice;
    }

    private void exitOfAdmin() {
        System.out.println();
        System.out.println("??? ???? tho??t kh???i h??? th???ng ADMIN !!!");
        System.out.println("--------------------");
        System.out.println();
        (new Login()).loginSystems();
        System.out.println();
    }

    private int choiceOfManage() {
        int choice = 0;
        while (true) {
            try {
                System.out.println(ANSI_CYAN + "???===================================================???");
                System.out.println("???         ??? ??? ??? ??? ??? QU???N L?? PH??NG ??? ??? ??? ??? ???         ???");
                System.out.println("???===================================================???");
                System.out.println("???>[1]. Th??m ph??ng                                   ???");
                System.out.println("???>[2]. S???a ph??ng                                    ???");
                System.out.println("???>[3]. X??a ph??ng                                    ???");
                System.out.println("???>[4]. Hi???n th??? danh s??ch ph??ng                     ???");
                System.out.println("???>[5]. T??m ki???m ph??ng c??n tr???ng theo gi??            ???");
                System.out.println("???>[6]. Ki???m tra tr???ng th??i ph??ng                    ???");
                System.out.println("???>[7]. Hi???n th??? to??n b???                             ???");
                System.out.println("???>[0]. Tho??t                                        ???");
                System.out.println("???===================================================???" + ANSI_RESET);
                System.out.println("[\uD83D\uDD11] Nh???p l???a ch???n:");
                choice = scan.nextInt();
                if (choice < 0 || choice > 7) throw new NumberFormatException();

                break;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println(ANSI_RED + "?????? Vui l??ng ch??? nh???p s??? ????? ch???n ch???c n??ng" + ANSI_RESET);
                System.out.println("--------------------");
                System.out.println();
                scan.nextLine();
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println(ANSI_RED +"?????? B???n ch??? ???????c nh???p t??? 0=>7"+ ANSI_RESET);
                System.out.println("--------------------");
                System.out.println();
            }
        }
        return choice;
    }

    private void menuRoomManager() {
        do {
            int choiceRoom = choiceOfManage();
            switch (choiceRoom) {
                case FIRST:
                    roomManager.addRoom();
                    break;
                case SECOND:
                    System.out.println("Nh???p Id ph??ng mu???n s???a:");
                    int idEdit = scan.nextInt();
                    roomManager.editRoom(idEdit);
                    break;
                case THIRD:
                    System.out.println("Nh???p Id ph??ng mu???n x??a:");
                    int idDelete = scan.nextInt();
                    roomManager.deleteByIdRoom(idDelete);
                    break;
                case FOURTH:
                    roomManager.displayRoomList();

                    break;
                case FIVE:
                    System.out.println("Nh???p gi?? tr??n : ");
                    double lowerPrice = scan.nextDouble();
                    System.out.println("Nh???p gi?? d?????i : ");
                    double abovePrice = scan.nextDouble();
                    if (lowerPrice > abovePrice) {
                        System.out.println(ANSI_RED +"?????? Nh???p sai d??? li???u m???i nh???p l???i !!"+ ANSI_RESET);
                        System.out.println("--------------------");
                        return;
                    }
                    roomManager.searchByPriceAndStatus(lowerPrice, abovePrice);
                    break;
                case SIXTH:
                    System.out.println("Nh???p t??n ph??ng:");
                    String name = scan.nextLine();
                    scan.nextLine();
                    System.out.println("Nh???p ng??y b???t ?????u(dd-mm-yyyy):");
                    String before = scan.nextLine();
                    LocalDate beforeDate = LocalDate.parse(before, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    System.out.println("Nh???p ng??y k???t th??c(dd-mm-yyyy):");
                    String after = scan.nextLine();
                    LocalDate afterDate = LocalDate.parse(after, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    billManager.checkRoomStatus(name, beforeDate, afterDate);
                    break;
                case SEVENTH:
                    roomManager.displayAll();
                    break;
                case ZERO:
                    menuOfAdmin();
                    break;
            }
        } while (true);
    }


    private int choiceOfBillManager() {
        int choice = 0;
        while (true) {
            try {
                System.out.println(ANSI_CYAN +"???===================================================???");
                System.out.println("???        ??? ??? ??? ??? ??? QU???N L?? H??A ????N ??? ??? ??? ??? ???        ???");
                System.out.println("???===================================================???");
                System.out.println("???>[1]. Th??m h??a ????n                                 ???");
                System.out.println("???>[2]. S???a h??a ????n                                  ???");
                System.out.println("???>[3]. X??a h??a ????n                                  ???");
                System.out.println("???>[4]. Hi???n th??? danh s??ch h??a ????n                   ???");
                System.out.println("???>[5]. Hi???n th??? danh s??ch h??a ????n theo ph??ng        ???");
                System.out.println("???>[0]. Tho??t                                        ???");
                System.out.println("???===================================================???"+ANSI_RESET);
                System.out.println("[\uD83D\uDD11] Nh???p l???a ch???n:");
                choice = scan.nextInt();
                if (choice < 0 || choice > 5) throw new NumberFormatException();
                break;

            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println(ANSI_RED + "?????? Vui l??ng ch??? nh???p s??? ????? ch???n ch???c n??ng" + ANSI_RESET);
                System.out.println("--------------------");
                System.out.println();
                scan.nextLine();
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println(ANSI_RED +"?????? B???n ch??? ???????c nh???p t??? 0=>5"+ ANSI_RESET);
                System.out.println("--------------------");
                System.out.println();
            }
        }
        return choice;
    }

    private void menuBillManager() {
        do {
            int choiceBill = choiceOfBillManager();
            switch (choiceBill) {
                case FIRST:
                    System.out.println("Nh???p v??o ph??ng mu???n thu??:");
                    String name = scan.nextLine();
                    Room room = roomManager.getRoom(name);
                    if (room != null) {
                        billManager.addBill(room);
                    } else {
                        System.out.println(ANSI_RED+"?????? Ph??ng tr??n kh??ng t???n t???i. Vui l??ng ?????t ph??ng kh??c!!"+ANSI_RESET);
                        System.out.println("--------------------");
                    }
                    break;
                case SECOND:
                    System.out.println("Nh???p Id h??a ????n mu???n s???a:");
                    int editId = scan.nextInt();
                    billManager.editBill(editId);
                    break;
                case THIRD:
                    System.out.println("Nh???p Id h??a ????n mu???n x??a:");
                    int deleteId = scan.nextInt();
                    billManager.deleteByIdBill(deleteId);
                    break;
                case FOURTH:
                    billManager.displayBillList();
                    break;
                case FIVE:
                    System.out.println("Nh???p t??n ph??ng:");
                    String roomNameSearch = scan.nextLine();
                    billManager.displayBillListByRoom(roomNameSearch);
                    break;
                case ZERO:
                    menuOfAdmin();
                    break;
            }
        } while (true);
    }


    private int choiceOfServiceManager() {
        int choice = 0;
        while (true) {
            try {
                System.out.println(ANSI_CYAN +"???===================================================???");
                System.out.println("???            ??? ??? ??? ??? ??? D???CH V??? ??? ??? ??? ??? ???            ???");
                System.out.println("???===================================================???");
                System.out.println("???>[1]. Th??m d???ch v???                                 ???");
                System.out.println("???>[2]. S???a d???ch v???                                  ???");
                System.out.println("???>[3]. X??a d???ch v???                                  ???");
                System.out.println("???>[4]. Hi???n th??? c??c d???ch v???                         ???");
                System.out.println("???>[0]. Tho??t                                        ???");
                System.out.println("???===================================================???"+ANSI_RESET);
                System.out.println("[\uD83D\uDD11] Nh???p l???a ch???n:");
                choice = scan.nextInt();
                if (choice < 0 || choice > 4) throw new NumberFormatException();
                break;
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println(ANSI_RED + "?????? Vui l??ng ch??? nh???p s??? ????? ch???n ch???c n??ng" + ANSI_RESET);
                System.out.println("--------------------");
                System.out.println();
                scan.nextLine();
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println(ANSI_RED +"?????? B???n ch??? ???????c nh???p t??? 0=>4"+ ANSI_RESET);
                System.out.println("--------------------");
                System.out.println();
            }
        }
        return choice;
    }

    private void menuServiceManager() {
        do {
            int choiceService = choiceOfServiceManager();
            switch (choiceService) {
                case FIRST:
                    serviceManager.addService();
                    break;
                case SECOND:
                    System.out.println("Nh???p t??n d???ch v??? mu???n s???a:");
                    String editName = scan.nextLine();
                    scan.nextLine();
                    serviceManager.editService(editName);
                    break;
                case THIRD:
                    System.out.println("Nh???p t??n d???ch v??? mu???n x??a:");
                    String deleteName = scan.nextLine();
                    scan.nextLine();
                    serviceManager.deleteServiceByName(deleteName);
                    break;
                case FOURTH:
                    serviceManager.displayServiceList();
                    break;
                case ZERO:
                    menuOfAdmin();
                    break;
            }
        } while (true);
    }


    private void menuOrderServiceManager() throws InputMismatchException {
        try {
            do {
                System.out.println(ANSI_CYAN +"???===================================================???");
                System.out.println("???             ??? ??? ??? ??? ??? ORDER ??? ??? ??? ??? ???             ???");
                System.out.println("???===================================================???");
                System.out.println("???>[1]. ?????t d???ch v???                                  ???");
                System.out.println("???>[2]. H???y d???ch v???                                  ???");
                System.out.println("???>[3]. Hi???n th??? c??c d???ch v??? ???? ?????t theo ph??ng       ???");
                System.out.println("???>[0]. Tho??t                                        ???");
                System.out.println("???===================================================???"+ANSI_RESET);
                System.out.println("[\uD83D\uDD11] Nh???p l???a ch???n:");
                int choiceOrderService = scan.nextInt();
                if (choiceOrderService < 0 || choiceOrderService > 3) {
                    System.out.println();
                    System.out.println(ANSI_RED +"?????? B???n ch??? ???????c nh???p t??? 0=>3"+ ANSI_RESET);
                    System.out.println("--------------------");
                    System.out.println();
                    menuOrderServiceManager();
                }
                switch (choiceOrderService) {
                    case FIRST:
                        System.out.println("Nh???p t??n ph??ng:");
                        String roomName = scan.nextLine();
                        System.out.println("Nh???p ng??y ?????t d???ch v???(dd-mm-yyyy):");
                        String date = scan.nextLine();
                        LocalDate orderDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        int choiceService = choiceService();
                        if (choiceService < 0 || choiceService > 8) {
                            System.out.println();
                            System.out.println(ANSI_RED+"?????? L???a ch???n kh??ng t???n t???i, m???i b???n nh???p l???i !!!"+ANSI_RESET);
                            System.out.println("--------------------");
                            System.out.println();
                            choiceService();
                        }
                        Bill bill = billManager.getBill(roomName, orderDate);
                        Service service = serviceManager.getService(choiceService);
                        if (bill != null && service != null) {
                            orderServiceManager.addOrderService(bill, service, orderDate);
                        } else {
                            System.out.println(ANSI_RED+"?????? Ph??ng tr??n kh??ng t???n t???i"+ANSI_RESET);
                            System.out.println("--------------------");
                        }
                        break;
                    case SECOND:
                        System.out.println("Nh???p t??n ph??ng:");
                        String deleteRoomName = scan.nextLine();
                        System.out.println("Nh???p t??n d???ch v???:");
                        String deleteService = scan.nextLine();
                        System.out.println("Nh???p ng??y ?????t d???ch v???(dd-mm-yyyy):");
                        String orderDate1 = scan.nextLine();
                        LocalDate orderDate2 = LocalDate.parse(orderDate1, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        orderServiceManager.deleteByRoomNameAndServiceName(deleteRoomName, deleteService, orderDate2);
                        break;
                    case THIRD:
                        System.out.println("Nh???p t??n ph??ng:");
                        String roomServiceName = scan.nextLine();
                        scan.nextLine();
                        System.out.println("Nh???p ng??y Check-in(dd-mm-yyyy):");
                        String start = scan.nextLine();
                        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        orderServiceManager.displayByRoomName(roomServiceName, startDate);
                        break;
                    case ZERO:
                        menuOfAdmin();
                        break;
                }
            } while (true);
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println();
            System.out.println(ANSI_RED+"?????? L???a ch???n kh??ng t???n t???i, m???i b???n nh???p l???i !!!"+ANSI_RESET);
            System.out.println("--------------------");
            System.out.println();
            menuOrderServiceManager();
        }
    }


    private int choiceService() throws InputMismatchException {
        int choiceService = 0;
        while (true) {
            try {
                System.out.println(ANSI_CYAN + "???===================================================???");
                System.out.println("???             ??? ??? ??? ??? ??? SERVICE ??? ??? ??? ??? ???           ???");
                System.out.println("???===================================================???");
                System.out.println("???>[1]. Coca                    >[5]. Vina           ???");
                System.out.println("???>[2]. 555                     >[6]. Massage Body   ???");
                System.out.println("???>[3]. H?? N???i City Tour        >[7]. M??? H???p Omachi  ???");
                System.out.println("???>[4]. Lavie                   >[8]. Bia Heniken    ???");
                System.out.println("???===================================================???" + ANSI_RESET);
                System.out.println("[\uD83D\uDD11] Nh???p l???a ch???n:");
                choiceService = scan.nextInt();
                break;
            } catch (InputMismatchException e) {
                scan.nextLine();
            }
        }
        return choiceService;
    }
}