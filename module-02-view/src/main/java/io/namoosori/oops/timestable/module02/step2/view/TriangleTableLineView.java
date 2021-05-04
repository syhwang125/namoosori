/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namoosori.oops.timestable.module02.step2.view;

import io.namoosori.oops.timestable.module02.step1.view.AbstractTableLineView;
import io.namoosori.oops.timestable.module02.step1.view.TableLineViewOption;
import io.namosoori.oops.timestable.module01.step1.domain.Equation;
import io.namosoori.oops.timestable.module01.step1.domain.Table;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static io.namosoori.oops.timestable.module01.step1.domain.Equation.END_RIGHT_NUMBER;
import static io.namosoori.oops.timestable.module01.step1.domain.Equation.START_RIGHT_NUMBER;
import static org.apache.commons.lang3.StringUtils.rightPad;

public class TriangleTableLineView extends AbstractTableLineView {
    //
    private static final int DefaultTableLength = 16;

    private int startIndex;
    private List<Iterator<String>> equationIteratorList;

    public TriangleTableLineView( TriangleTableLineView parentView ) {
        //
        this( parentView.getTableLineViewOption() );
        this.startIndex = parentView.getStartIndex() - 1;
        super.setColumnCount( parentView.getColumnCount() + 1 );
    }

    public TriangleTableLineView( TableLineViewOption tableLineViewOption ) {
        //
        super( tableLineViewOption );
        this.startIndex = 3;
        super.setColumnCount( 1 );
    }

    public int getStartIndex() {
        //
        return startIndex;
    }

    @Override
    public void takeUnitTables( LinkedList<Table> sourceTables ) {
        //
        List<Table> tables = new ArrayList<>();
        for ( int i = 0; i < super.getColumnCount(); i++ ) {
            tables.add( sourceTables.removeFirst() );
            if ( sourceTables.size() == 0 ) {
                break;
            }
        }

        this.equationIteratorList = tables.stream()
                .map( table -> table.requestFormattedEquations().iterator() )
                .collect( Collectors.toList() );

    }

    @Override
    public void showTableLine() {
        //
        for ( int i = START_RIGHT_NUMBER; i <= END_RIGHT_NUMBER; i++ ) {
            System.out.println( this.buildLine() );
        }
    }

    private String buildLine() {
        StringBuilder builder = new StringBuilder();

        for ( int index = 0; index < 7; index++ ) {
            if ( index < startIndex ) {
                builder.append( rightPad( "", DefaultTableLength ) );
                continue;
            }
            builder.append( rightPad( this.buildEquationsLine(), DefaultTableLength ) );
            break;
        }

        return builder.toString();
    }

    private String buildEquationsLine() {
        StringBuilder builder = new StringBuilder();
        for ( Iterator<String> equationIterator : equationIteratorList ) {
            builder.append( rightPad( equationIterator.next(), DefaultTableLength ) );
            builder.append( rightPad( "", DefaultTableLength ) );
        }

        return builder.toString();
    }

}