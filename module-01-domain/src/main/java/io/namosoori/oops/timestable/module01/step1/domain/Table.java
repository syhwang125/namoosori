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

import static io.namosoori.oops.timestable.module01.step1.domain.Equation.END_RIGHT_NUMBER;
import static io.namosoori.oops.timestable.module01.step1.domain.Equation.START_RIGHT_NUMBER;
import static java.util.Collections.reverse;
import static java.util.stream.Collectors.toList;

@Getter
public class Table implements JsonSerializable {

    private int leftNumber;
    @Setter
    private Format format;
    @Setter
    private SortOrder equationOrder;
    private Map<Integer, Equation> equationMap;

    public Table( int leftNumber ) {
        this( leftNumber, Format.InMath );
    }

    public Table( int leftNumber, Format format ) {
        this.leftNumber = leftNumber;
        this.format = format;
        this.equationMap = new LinkedHashMap<>();
        this.initialize();
    }

    private void initialize() {
        for ( int rightNumber = START_RIGHT_NUMBER; rightNumber <= END_RIGHT_NUMBER; rightNumber++ ) {
            equationMap.put( rightNumber, new Equation( leftNumber, rightNumber ) );
        }
    }

    public List<Equation> requestEquations() {
        List<Equation> equationList = equationMap.values()
                .stream()
                .collect( toList() );

        if ( !equationOrder.isAscending() ) {
            reverse( equationList );
        }
        return equationList;
    }

    public List<String> requestFormattedEquations() {
        return this.requestEquations()
                .stream()
                .map( equation -> equation.toFormatString( format ) )
                .collect( toList() );
    }

    public String requestFormattedEquation( int rightNumber ) {
        return this.requestFormattedEquations().get( rightNumber - 1 );
    }
}