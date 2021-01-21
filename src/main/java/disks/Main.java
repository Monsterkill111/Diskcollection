package disks;

import disks.domain.Disk;
import disks.domain.DiskType;
import disks.domain.InformationType;
import disks.mysql.MySqlDaoFactory;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static final MySqlDaoFactory factory = MySqlDaoFactory.instance;

    private static String readData(String name) {
        System.out.printf("%s: ", name);
        return new Scanner(System.in).nextLine();
    }

    private static void selectDiskTypes() {
        for (DiskType DiskType : factory.getDao(factory.getContext(), DiskType.class).selectAll()) {
            System.out.printf("id=%d|title=%s\n", DiskType.getId(), DiskType.getDescription());
        }
    }
    private static void selectInformationTypes() {
        for (InformationType InformationType : factory.getDao(factory.getContext(), InformationType.class).selectAll()) {
            System.out.printf("id=%d|title=%s\n", InformationType.getId(), InformationType.getDescription());
        }
    }
    private static void selectDisks() {
        for (Disk Disk : factory.getDao(factory.getContext(), Disk.class).selectAll()) {
            System.out.printf("id=%d|DiskType_id=%d|title=%s|description=%s|InformationType_id=%d\n",
                    Disk.getId(),
                    Disk.getDiskType().getId(),
                    Disk.getTitle(),
                    Disk.getDescription(),
                    Disk.getInformationType().getId());
        }
    }
    private static void insertDiskType() {
        var DiskType = new DiskType();
        DiskType.setDescription(readData("Введите тип диска"));
        factory.getDao(factory.getContext(), DiskType.class).insert(DiskType);
    }
    private static void insertInformationType() {
        var InformationType = new InformationType();
        InformationType.setDescription(readData("Введите тип информации"));
        factory.getDao(factory.getContext(), InformationType.class).insert(InformationType);
    }
    private static void insertDisk() {
        var Disk = new Disk();
        Disk.setTitle(readData("Введите название диска"));
        Disk.setDescription(readData("Введите описание диска"));
        var DiskTypeId = Integer.parseInt(readData("Введите id типа диска"));
        var DiskType = factory.getDao(factory.getContext(), DiskType.class).getObjectById(DiskTypeId);
        if (DiskType == null) {
            System.out.println("Неизвестный тип диска");
            return;
        }
        Disk.setDiskType(DiskType);
        var InformationTypeId = Integer.parseInt(readData("Введите id типа информации"));
        var InformationType = factory.getDao(factory.getContext(), InformationType.class).getObjectById(InformationTypeId);
        if (InformationType == null) {
            System.out.println("Неизвестный тип информации");
            return;
        }
        Disk.setInformationType(InformationType);
        factory.getDao(factory.getContext(), Disk.class).insert(Disk);
    }
    private static void updateDiskType() {
        var DiskType = factory.getDao(factory.getContext(), DiskType.class).getObjectById(Integer.parseInt(readData("Введите id изменяемого объекта")));
        if (DiskType == null) {
            System.out.println("Неизвестный тип диска");
            return;
        }
        DiskType.setDescription(readData("Введите тип диска"));
        factory.getDao(factory.getContext(), DiskType.class).update(DiskType);
    }
    private static void updateInformationType() {
        var InformationType = factory.getDao(factory.getContext(), InformationType.class).getObjectById(Integer.parseInt(readData("Введите id изменяемого объекта")));
        if (InformationType == null) {
            System.out.println("Неизвестный тип информации");
            return;
        }
        InformationType.setDescription(readData("Введите тип информации"));
        factory.getDao(factory.getContext(), InformationType.class).update(InformationType);
    }
    private static void updateDisk() {
        var Disk = factory.getDao(factory.getContext(), Disk.class).getObjectById(Integer.parseInt(readData("Введите id изменяемого объекта")));
        if (Disk == null) {
            System.out.println("Неизвестная диск");
            return;
        }
        Disk.setTitle(readData("Введите название диска"));
        Disk.setDescription(readData("Введите описание диска"));
        var DiskTypeId = Integer.parseInt(readData("Введите id типа диска"));
        var DiskType = factory.getDao(factory.getContext(), DiskType.class).getObjectById(DiskTypeId);
        if (DiskType == null) {
            System.out.println("Неизвестный тип диска");
            return;
        }
        Disk.setDiskType(DiskType);
        var InformationTypeId = Integer.parseInt(readData("Введите id типа информации"));
        var InformationType = factory.getDao(factory.getContext(), InformationType.class).getObjectById(InformationTypeId);
        if (InformationType == null) {
            System.out.println("Неизвестный тип информации");
            return;
        }
        Disk.setInformationType(InformationType);
        factory.getDao(factory.getContext(), Disk.class).update(Disk);
    }
    private static void deleteDiskTypes() {
        var DiskType = factory.getDao(factory.getContext(), DiskType.class).getObjectById(Integer.parseInt(readData("Введите id удаляемого объекта")));
        if (DiskType == null) {
            System.out.println("Неизвестный тип диска");
            return;
        }
        factory.getDao(factory.getContext(), DiskType.class).delete(DiskType);
    }
    private static void deleteInformationTypes() {
        var InformationType = factory.getDao(factory.getContext(), InformationType.class).getObjectById(Integer.parseInt(readData("Введите id удаляемого объекта")));
        if (InformationType == null) {
            System.out.println("Неизвестный тип информации");
            return;
        }
        factory.getDao(factory.getContext(), InformationType.class).delete(InformationType);
    }
    private static void deleteDisks() {
        var Disk = factory.getDao(factory.getContext(), Disk.class).getObjectById(Integer.parseInt(readData("Введите id удаляемого объекта")));
        if (Disk == null) {
            System.out.println("Неизвестная диск");
            return;
        }
        factory.getDao(factory.getContext(), Disk.class).delete(Disk);
    }

    public static HashMap<Integer, String> helpers = new HashMap<>() {{
        put(1, "1 = select DiskTypes");
        put(2, "2 = select InformationTypes");
        put(3, "3 = select Disks");
        put(4, "4 = insert DiskType");
        put(5, "5 = insert InformationType");
        put(6, "6 = insert Disk");
        put(7, "7 = update DiskType");
        put(8, "8 = update InformationType");
        put(9, "9 = update Disk");
        put(10, "10 = delete DiskType");
        put(11, "11 = delete InformationType");
        put(12, "12 = delete Disk");
    }};
    public static HashMap<Integer, Runnable> actions = new HashMap<>() {{
        put(1, Main::selectDiskTypes);
        put(2, Main::selectInformationTypes);
        put(3, Main::selectDisks);
        put(4, Main::insertDiskType);
        put(5, Main::insertInformationType);
        put(6, Main::insertDisk);
        put(7, Main::updateDiskType);
        put(8, Main::updateInformationType);
        put(9, Main::updateDisk);
        put(10, Main::deleteDiskTypes);
        put(11, Main::deleteInformationTypes);
        put(12, Main::deleteDisks);
    }};

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        helpers.values().forEach(System.out::println);
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                var a = scanner.nextInt();
                actions.entrySet().stream().filter(x -> x.getKey()==a).findFirst().ifPresentOrElse(
                        x -> x.getValue().run(),
                        () -> System.out.println("Unknown action")
                );
            } catch (Exception ex) {
                System.out.println("Unknown action");
            }
            helpers.values().forEach(System.out::println);
        }
    }
}
