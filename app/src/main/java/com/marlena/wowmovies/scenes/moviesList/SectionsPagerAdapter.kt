package com.marlena.wowmovies.scenes.moviesList

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.marlena.wowmovies.R


class SectionsPagerAdapter(fm: FragmentManager, val view: MoviesListFragment) : FragmentPagerAdapter(fm) {

    private val actionListFragment by lazy {MoviesListFragment.newInstance(0) }
    private val dramaListFragment by lazy {MoviesListFragment.newInstance(1) }
    private val fantasyListFragment by lazy {MoviesListFragment.newInstance(2) }
    private val fictionListFragment by lazy {MoviesListFragment.newInstance(3) }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> actionListFragment
            1 -> dramaListFragment
            2 -> fantasyListFragment
            3 -> fictionListFragment
            else -> Fragment()
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> view.getViewContext()?.getString(R.string.tab_action)
            1 -> view.getViewContext()?.getString(R.string.tab_drama)
            2 -> view.getViewContext()?.getString(R.string.tab_fantasy)
            3 -> view.getViewContext()?.getString(R.string.tab_fiction)
            else -> null
        }
    }
}