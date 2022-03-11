package com.sky.xs.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.sky.common.GsonUtils
import com.sky.common.LogUtils
import com.sky.xs.entity.PoetryEntity
import com.sky.xs.fragment.NotesChildFragment

class NotesFragmentAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(
    manager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    var maps: LinkedHashMap<String, ArrayList<PoetryEntity>>? = null
        set(value) {
            field = value
            val keys: Set<String> = maps!!.keys
            titles.clear()
            titles.addAll(keys)
//            LogUtils.i("titles==${titles}")
//        Collections.sort(titles);
            notifyDataSetChanged()
        }
    var titles: MutableList<String> = mutableListOf()
    override fun getCount(): Int = maps?.size ?: 0
    override fun getItem(position: Int): Fragment =
        NotesChildFragment.newInstance(GsonUtils.toJson(maps?.get(titles[position])))

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position].split("/").last()
    }

}
