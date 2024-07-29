package com.cy.store.service.impl;

import com.cy.store.Vo.CartVo;
import com.cy.store.entity.Cart;
import com.cy.store.mapper.CartMapper;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.ICartService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        //        检查要添加的商品是否已经在购物车中存在
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        Date date = new Date();
        if (result == null) {
            Cart cart = new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            cart.setPrice(productMapper.findById(pid).getPrice());

            cart.setCreatedTime(date);
            cart.setModifiedTime(date);
            cart.setModifiedUser(username);
            cart.setCreatedUser(username);
            // 如果不存在，则新增购物车记录
            Integer row = cartMapper.insert(cart);
            if (row != 1) {
                throw new InsertException("插入时错误");
            }
        } else {
            // 如果存在，则更新数量
            Integer row = cartMapper.updateNumByCid(result.getCid(), amount, date);
            if (row != 1) {
                throw new UpdateException("更新时错误");
            }
        }
    }

    @Override
    public List<CartVo> getVoByUid(Integer uid) {
        return cartMapper.findByUid(uid);
    }
}
