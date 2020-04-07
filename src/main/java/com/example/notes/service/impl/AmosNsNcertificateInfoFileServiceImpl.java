package com.example.notes.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.notes.entity.AmosNsNcertificateFile;
import com.example.notes.entity.AmosNsNcertificateInfoFile;
import com.example.notes.mapper.AmosNsNcertificateFileMapper;
import com.example.notes.mapper.AmosNsNcertificateInfoFileMapper;
import com.example.notes.service.IAmosNsNcertificateFileService;
import com.example.notes.service.IAmosNsNcertificateInfoFileService;
import org.springframework.stereotype.Service;

@Service
public class AmosNsNcertificateInfoFileServiceImpl extends ServiceImpl<AmosNsNcertificateInfoFileMapper, AmosNsNcertificateInfoFile> implements IAmosNsNcertificateInfoFileService {
}
