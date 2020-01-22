package schoolmgmt;

import Dao.*;
import UI.Menu;
import schoolmgmt.domain.*;

public class main {

    public static void main(String[] args) {
        SampleData.add();
        Menu.mainMenu();

        StudentDao sDao = new StudentDao();

        //TODO Fixa nullpointer på visa alla educations, kurser och studenter. Om en education tas bort ska det stå null.
        
    }
}
