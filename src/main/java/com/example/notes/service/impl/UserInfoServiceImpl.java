package com.example.notes.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.notes.entity.UserInfoDeal;
import com.example.notes.mapper.UserInfoMapper;
import com.example.notes.service.IUserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoDeal> implements IUserInfoService {



}
