package io.namoosori.oops.timestable.module03.step2;

import io.namosoori.oops.timestable.module01.step1.domain.Equation;
import io.namosoori.oops.timestable.module01.step1.domain.TimesTable;

public class MenuConsole {
    //
    private MenuBar menuBar;

    public MenuConsole() {
        //
        this.menuBar = buildMenuBar();
    }

    public void showMenuBar() {
        //
        menuBar.show();
    }

    private MenuBar buildMenuBar() {
        //
        MenuBar menuBar = new MenuBar("Timestable menu");
        menuBar.addMenu( ColumnTableMenu.buildMenu() );
        menuBar.addMenu( SquareTableMenu.buildMenu() );
        menuBar.addMenu( TriangleTableMenu.buildMenu() );
        menuBar.addMenu( ExitMenu.buildMenu() );

        return menuBar;
    }

    public static void main(String[] args) {
        //
        MenuConsole menuConsole = new MenuConsole();
        menuConsole.showMenuBar();
    }
}