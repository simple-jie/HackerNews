package com.simple_jie.hackernews.di;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Xingbo.Jie on 25/2/17.
 */

public interface HasComponent<Component> {
    Component getComponent();
}
