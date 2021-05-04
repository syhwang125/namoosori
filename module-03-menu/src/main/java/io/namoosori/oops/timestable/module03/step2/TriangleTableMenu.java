package io.namoosori.oops.timestable.module03.step2;

import io.namoosori.oops.timestable.module02.step1.view.ConsoleView;
import io.namoosori.oops.timestable.module02.step1.view.TableLineType;
import io.namoosori.oops.timestable.module02.step1.view.TableLineViewOption;
import io.namosoori.oops.timestable.module01.step1.domain.TableOption;
import io.namosoori.oops.timestable.module01.step1.domain.TimesTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TriangleTableMenu extends AbstractMenu {
    //
    public TriangleTableMenu( int sequence, String title ) {
        //
        super( sequence, title );
    }

    public TriangleTableMenu( int sequence, String title, String exitMessage ) {
        //
        super( sequence, title, exitMessage );
    }

    public static TriangleTableMenu buildMenu() {
        //
        TriangleTableMenu triangleTableMenu = new TriangleTableMenu( 3, "Triangle table " );
        triangleTableMenu.add( OptionItem.newInstance( OptionType.TableFormat ) );
        triangleTableMenu.add( OptionItem.newInstance( OptionType.TableOrder ) );
        triangleTableMenu.add( OptionItem.newInstance( OptionType.EquationOrder ) );

        return triangleTableMenu;
    }

    @Override
    public ConsoleView buildConsoleView() {
        //
        TableLineViewOption tableLineViewOption = new TableLineViewOption( TableLineType.Triangle );
        super.assignTableViewOptionValue( tableLineViewOption );

        ConsoleView consoleView = new ConsoleView( tableLineViewOption );
        return consoleView;
    }

    @Override
    public TimesTable buildTimesTable() {
        //
        TableOption tableOption = new TableOption();
        super.assignTableOptionValue( tableOption );

        TimesTable timesTable = new TimesTable( 1, 9 );
        timesTable.setTableOption( tableOption );

        return timesTable;
    }


}