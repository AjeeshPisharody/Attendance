package com.rowsun.attendance;

import android.os.Environment;

import com.aspose.cells.Cell;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

import java.io.File;

/**
 * Created by ROW SUN on 1/27/2015.
 */
public class Addstudent {
    public Addstudent(String name,int n) throws Exception {

        String sdPath = Environment.getExternalStorageDirectory().getPath() + File.separator + "Attendance" + File.separator;
        Workbook workbook = new Workbook(sdPath + "karkhanaAttendance.xlsx");
        Worksheet worksheet = workbook.getWorksheets().get(n);
        int i = 0, j = 0;
        //String[] arr = new String[worksheet.getCells().getMaxRow()];
        int column = worksheet.getCells().getMaxColumn();
        int row=worksheet.getCells().getMaxRow();
            Cell cell = worksheet.getCells().get(row+1, 0);
            cell.putValue(name);
            workbook.save(sdPath + "karkhanaAttendance.xlsx");
        }
}

