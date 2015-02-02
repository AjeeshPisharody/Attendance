package com.rowsun.attendance;

import android.os.Environment;

import com.aspose.cells.Workbook;
import java.io.File;
/**
 * Created by ROW SUN on 1/29/2015.
 */
public class clearData {
    public void clearAll() throws Exception

    {
        String sdPath = Environment.getExternalStorageDirectory().getPath() + File.separator + "Attendance" + File.separator;

        Workbook workbook = new Workbook(sdPath + "karkhanaAttendance.xlsx");
        for (int i = 10; i < workbook.getWorksheets().getCount(); i++) {
            workbook.getWorksheets().removeAt(i);
            workbook.save(sdPath + "karkhanaAttendance.xlsx");


        }
    }
}