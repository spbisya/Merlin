package com.foxfinity.merlin.definition;

import com.foxfinity.merlin.base.IPresenter;
import com.foxfinity.merlin.base.IView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Project Merlin. Created by Izya Pitersky on 3/19/17.
 */

public interface DefinitionContract {
    interface IDefinitionView extends IView<IDefinitionPresenter> {
        void showNetworkError();

        void startRefreshing();

        void stopRefreshing();

        void showDefinitionAnswer(HashMap<String, ArrayList<String>> sortedDefinitions);

    }

    interface IDefinitionPresenter extends IPresenter<IDefinitionView> {
        void searchDefinition(String word);
    }

}
