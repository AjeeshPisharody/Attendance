package com.rowsun.attendance;

import android.os.Environment;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;

import java.io.File;

/**
 * Created by ROW SUN on 1/24/2015.
 */
public class generateExcel {
    generateExcel()throws Exception
    {
        String sdPath = Environment.getExternalStorageDirectory().getPath() + File.separator+"Attendance"+File.separator;
        Workbook workbook=new Workbook();
           int j=1;
        WorksheetCollection sheets=workbook.getWorksheets();
        for(int i=0;i<10;i++)
        {
            sheets.add();
            sheets.get(i).setName("Sheet"+j);
            j++;

        }
        workbook.save(sdPath+"karkhanaAttendance.xlsx");
    }
}
