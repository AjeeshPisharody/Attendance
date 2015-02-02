package com.rowsun.attendance;

import android.os.Environment;
import com.aspose.cells.Cell;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import java.io.File;
public class excelRead {
    public String[] excelRead(int n) throws Exception {

        String sdPath = Environment.getExternalStorageDirectory().getPath() + File.separator+"Attendance"+File.separator;
        Workbook workbook = new Workbook(sdPath + "karkhanaAttendance.xlsx");
        Worksheet worksheet = workbook.getWorksheets().get(n);
        int i = 0,j=0;
        String[] arr = new String[worksheet.getCells().getMaxRow()+1];
        for(i=0;i<worksheet.getCells().getMaxRow()+1;i++)
        {
            Cell cell = worksheet.getCells().get(i,0);
            arr[i]=cell.getStringValue().toString();

        }

        return arr;
    }
    public String sheetName(int n) throws Exception {
        String sdPath = Environment.getExternalStorageDirectory().getPath() + File.separator + "Attendance" + File.separator;
        Workbook workbook = new Workbook(sdPath + "karkhanaAttendance.xlsx");
        Worksheet worksheet = workbook.getWorksheets().get(n);
        return worksheet.getName();
    }

}
