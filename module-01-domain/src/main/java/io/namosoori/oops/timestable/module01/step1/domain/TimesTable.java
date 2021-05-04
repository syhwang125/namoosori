/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */
package io.namosoori.oops.timestable.module01.step1.domain;

import io.namosoori.oops.timestable.module01.util.JsonSerializable;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.namosoori.oops.timestable.module01.step1.domain.Equation.DEFAULT_START_LEFT_NUMBER;
import static io.namosoori.oops.timestable.module01.step1.domain.Equation.END_LEFT_NUMBER;
import static java.util.Collections.reverse;
import static java.util.stream.Collectors.toList;

@Getter
@Setter
public class TimesTable implements JsonSerializable {
    //
    private int startLeftNumber;
    private int endLeftNumber;
    private TableOption tableOption;
    private Map<Integer, Table> tableMap;

    public TimesTable() {
        this( DEFAULT_START_LEFT_NUMBER, END_LEFT_NUMBER );
    }

    public TimesTable( int startLeftNumber, int endLeftNumber ) {
        this.startLeftNumber = startLeftNumber;
        this.endLeftNumber = endLeftNumber;
        this.tableOption = new TableOption();
        this.tableMap = new LinkedHashMap<>();
        this.initialize();
    }

    private void initialize() {
        for ( int leftNumber = startLeftNumber; leftNumber <= endLeftNumber; leftNumber++ ) {
            tableMap.put( leftNumber, new Table( leftNumber ) );
        }
    }

    public List<Table> requestTables( int startLeftNumber ) {
        //
        return this.requestTables()
                .stream()
                .filter( table -> table.getLeftNumber() >= startLeftNumber )
                .collect( toList() );
    }


    public List<Table> requestTables() {
        List<Table> tableList = tableMap.values()
                .stream()
                .collect( toList() );

        tableList.forEach( table -> {
            table.setFormat( tableOption.getTableFormat() );
            table.setEquationOrder( tableOption.getEquationOrder() );
        } );

        if ( !tableOption.getTableOrder().isAscending() ) {
            reverse( tableList );
        }

        return tableList;
    }

}