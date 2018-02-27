/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.znima;

import com.znima.dto.GetNovelConfigDto;
import com.znima.dto.NovelItemDto;
import com.znima.entity.NovelItem;
import com.znima.service.NovelService;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class AddNovelThread implements Runnable{

    private List<NovelItemDto> itemDtos;
    private GetNovelConfigDto configDto;
    private Integer novelId;
    private NovelItem lastItem;
    
    private AddNovelThread() {
        
    }
    
    public AddNovelThread(List<NovelItemDto> itemDtos, GetNovelConfigDto configDto, Integer novelId, NovelItem lastItem) {
        this.configDto = configDto;
        this.itemDtos = itemDtos;
        this.lastItem = lastItem;
        this.novelId = novelId;
    }
    
    @Override
    public void run() {
        NovelService.saveItem(itemDtos, configDto, novelId, lastItem);
    }
    
}
