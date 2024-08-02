package com.cy.store.service.impl;

import com.cy.store.Vo.CartVo;
import com.cy.store.entity.Cart;
import com.cy.store.mapper.CartMapper;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.ICartService;
import com.cy.store.service.ex.*;
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

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if(result == null){
            throw new ProductNotFoundException("数据不存在");
        }

        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }

        Integer num = result.getNum() + 1;
        Integer row = cartMapper.updateNumByCid(cid, num, new Date());
        if(row != 1){
            throw new UpdateException("更新时错误");
        }
        //返回新的总量
        return num;
    }

    @Override
    public Integer minusNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if(result == null){
            throw new ProductNotFoundException("数据不存在");
        }

        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }

        Integer num = result.getNum() - 1;
        if(num != 0) {
            Integer row = cartMapper.updateNumByCid(cid, num, new Date());
            if(row != 1){
                throw new UpdateException("更新时错误");
            }
        }else {
            Integer row = cartMapper.deleteNumByCid(cid);
            if(row != 1){
                throw new DeleteException("删除时错误");
            }
        }
        //返回新的总量
        return num;
    }

    @Override
    public List<CartVo> getVoByCid(Integer uid, Integer[] cids) {
        List<CartVo> list = cartMapper.findVoByCid(cids);
        list.removeIf(cartVo -> !cartVo.getUid().equals(uid));
        return list;
    }

    @Override
    public void deleteNum(Integer cid) {
        Integer row = cartMapper.deleteNumByCid(cid);
        if(row != 1){
            throw new DeleteException("删除时错误");
        }
    }
}
