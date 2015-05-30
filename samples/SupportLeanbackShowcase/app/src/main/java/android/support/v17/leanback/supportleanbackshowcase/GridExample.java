/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package android.support.v17.leanback.supportleanbackshowcase;

import android.os.Bundle;
import android.support.v17.leanback.app.VerticalGridFragment;
import android.support.v17.leanback.supportleanbackshowcase.cards.models.CardRow;
import android.support.v17.leanback.supportleanbackshowcase.cards.presenters.CardPresenterSelector;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.FocusHighlight;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v17.leanback.widget.VerticalGridPresenter;

import com.google.gson.Gson;

/**
 * An example how to use leanback's {@link VerticalGridFragment}.
 */
public class GridExample extends VerticalGridFragment {

    private static final String TAG = "GridExample";
    private static final int COLUMNS = 4;
    private static final int ZOOM_FACTOR = FocusHighlight.ZOOM_FACTOR_NONE;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.grid_example_title));
        setupRowAdapter();
    }

    private void setupRowAdapter() {
        VerticalGridPresenter gridPresenter = new VerticalGridPresenter(ZOOM_FACTOR);
        gridPresenter.setNumberOfColumns(COLUMNS);
        setGridPresenter(gridPresenter);

        PresenterSelector cardPresenterSelector = new CardPresenterSelector(getActivity());
        ArrayObjectAdapter adapter = new ArrayObjectAdapter(cardPresenterSelector);
        String json = Utils.inputStreamToString(getResources().openRawResource(R.raw.grid_example));
        CardRow row = new Gson().fromJson(json, CardRow.class);
        adapter.addAll(0, row.getCards());
        setAdapter(adapter);
    }

}
