package com.foxfinity.merlin.search;

import com.foxfinity.merlin.BasePresenter;
import com.foxfinity.merlin.BaseView;
import com.foxfinity.merlin.models.Word;

import java.util.List;

/**
 * Project Merlin. Created by Izya Pitersky on 3/18/17.
 * This specifies the contract between the view and the presenter.
 */

public interface SearchContract {
    interface View extends BaseView<Presenter> {
        void showWords(List<Word> words);

        void showNoWords();

        void showNetworkProblemToast();

    }

    interface Presenter extends BasePresenter {
        void searchExpression(String expression);
    }
}
