package com.rowsun.attendance;

import android.os.Environment;

import com.aspose.cells.Cell;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

import java.io.File;
import android.os.Environment;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.ColumnCollection;
import com.aspose.cells.SaveFormat;
import java.io.File;

/**
 * Created by ROW SUN on 1/22/2015.
 */
public class excelWrite {
        public void write(int[] pos,int n) throws Exception {

            String sdPath = Environment.getExternalStorageDirectory().getPath() + File.separator+"Attendance"+File.separator;
            Workbook workbook = new Workbook(sdPath + "karkhanaAttendance.xlsx");
            Worksheet worksheet = workbook.getWorksheets().get(n);
            int i = 0,j=0;
            //String[] arr = new String[worksheet.getCells().getMaxRow()];
            int column=worksheet.getCells().getMaxColumn();
           for(i=0;i<worksheet.getCells().getMaxRow()+1;i++)
           {
                Cell cell = worksheet.getCells().get(pos[i],column+1);
                cell.putValue("P");
               workbook.save(sdPath+"karkhanaAttendance.xlsx");
            }


    }


}
