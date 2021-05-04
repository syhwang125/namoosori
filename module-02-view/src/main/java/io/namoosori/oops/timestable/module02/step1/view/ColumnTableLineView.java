/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namoosori.oops.timestable.module02.step1.view;

import io.namosoori.oops.timestable.module01.step1.domain.Table;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static io.namosoori.oops.timestable.module01.step1.domain.Equation.END_RIGHT_NUMBER;
import static io.namosoori.oops.timestable.module01.step1.domain.Equation.START_RIGHT_NUMBER;

public class ColumnTableLineView extends AbstractTableLineView {
    //
    private static final int DefaultTableLength = 16;

    private List<Table> tables;

    public ColumnTableLineView( TableLineViewOption tableLineViewOption ) {
        //
        super( tableLineViewOption );
        this.tables = new ArrayList<>();
    }

    @Override
    public void takeUnitTables( LinkedList<Table> tables ) {
        for ( int i = 0; i < super.getColumnCount(); i++ ) {
            this.tables.add( tables.removeFirst() );
            if ( tables.size() == 0 ) {
                break;
            }
        }
    }

    @Override
    public void showTableLine() {
        for ( int rightNumber = START_RIGHT_NUMBER; rightNumber <= END_RIGHT_NUMBER; rightNumber++ ) {
            StringBuilder builder = new StringBuilder();
            for ( Table table : tables ) {
                builder.append( table.requestFormattedEquation( rightNumber ) );
                builder.append( "    " );
            }
            System.out.println( builder.toString() );
        }
        System.out.println("...");
    }

}