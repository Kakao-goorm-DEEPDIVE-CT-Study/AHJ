package com.example.exp2.EXP1.service;

import com.example.exp2.EXP1.model.Memo;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemoService {
    private final List<Memo> memoList = new ArrayList<>();

    public List<Memo> showMemoList(){
        return memoList;
    }
    public Memo getMemo(int choice){
            return memoList.get(choice);
    }
    public void addMemo(Memo memo){
        memoList.add(memo);
    }
    public void editMemo(Memo memo,int index){
        Memo targetMemo = memoList.get(index);
        targetMemo.setTitle(memo.getTitle());
        targetMemo.setContent(memo.getContent());
        targetMemo.setWriter(memo.getWriter());
        targetMemo.setWriteDate(memo.getWriteDate());
    }
    public void removeMemo(int index){
        memoList.remove(index);
    }
}
