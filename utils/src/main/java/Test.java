import de.enwaffel.projectp.api.guardiencraft.GCDiscordStats;
import de.enwaffel.randomutils.file.FileOrPath;
import de.enwaffel.randomutils.file.FileUtil;
import de.enwaffel.script.ScriptSource;
import de.enwaffel.script.Script;
import org.usb4java.*;

public class Test {

    public static Context context;

    public static void main(String[] args) {
        /*
        MySQL sql = SQL.connect(MySQL.class, "192.168.30.42", "guardiencraft", "guardiencraft", "guardiencraft_data");
        SQLCreateTableTask createTableTask1 = sql.newCreateTableTask("guardiencraft_dc_data", new String[]{"UserID", "job", "money", "timesWorked", "uuid", "level", "xp", "xpMulti", "totalXP"},
                new String[]{"18", "20", null, "18", "40", "18", "18", "18", "18"}, SQLDataType.BIGINT, SQLDataType.VARCHAR, SQLDataType.DOUBLE, SQLDataType.BIGINT, SQLDataType.VARCHAR, SQLDataType.BIGINT, SQLDataType.BIGINT, SQLDataType.BIGINT, SQLDataType.BIGINT);
        createTableTask1.complete();
         */



        /*
        context = new Context();
        int result = LibUsb.init(context);
        if (result != LibUsb.SUCCESS) throw new LibUsbException("Unable to initialize libusb.", result);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> LibUsb.exit(context)));
        Device device = findDevice((short) 1444,  (short) 39041);
        DeviceHandle handle = new DeviceHandle();
        int result1 = LibUsb.open(device, handle);
        if (result1 != LibUsb.SUCCESS) throw new LibUsbException("Unable to open USB device", result);

ScriptSource source = Script.load(FileUtil.readFile(FileOrPath.path("test.scr")));

        Script.run(source);
         */


    }

    /*
    public static Device findDevice(short vendorId, short productId)
    {
        // Read the USB device list
        DeviceList list = new DeviceList();
        int result = LibUsb.getDeviceList(context, list);
        if (result < 0) throw new LibUsbException("Unable to get device list", result);
        try
        {
            // Iterate over all devices and scan for the right one
            for (Device device: list)
            {
                DeviceDescriptor descriptor = new DeviceDescriptor();
                result = LibUsb.getDeviceDescriptor(device, descriptor);
                if (result != LibUsb.SUCCESS) throw new LibUsbException("Unable to read device descriptor", result);
                System.out.println(descriptor.idVendor() + " | " + descriptor.idProduct());
                if (descriptor.idVendor() == vendorId && descriptor.idProduct() == productId) return device;
            }
        }
        finally
        {
            // Ensure the allocated device list is freed
            LibUsb.freeDeviceList(list, true);
        }

        // Device not found
        return null;
    }
     */

}
