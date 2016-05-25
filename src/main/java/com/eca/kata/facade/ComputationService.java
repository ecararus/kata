package com.eca.kata.facade;

import com.eca.kata.vo.Basket;

import java.util.List;

public interface ComputationService {

    Basket compute(List<String> itemsName);

}
