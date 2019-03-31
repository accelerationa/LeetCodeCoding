package com.company;

public class IntegerResultValidator implements Validator<Integer>{
    @Override
    public void checkResult(Integer rootA, Integer rootB) {
        assert rootA.equals(rootB);
    }
}
