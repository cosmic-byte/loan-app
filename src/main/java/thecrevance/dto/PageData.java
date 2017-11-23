package thecrevance.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Greg on 10/07/17.
 */
public class PageData<T> {
    private List<T> content;
    private int currentNumber;
    private int beginIndex;
    private int endIndex;
    private Long total;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(int currentNumber) {
        this.currentNumber = currentNumber;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public PageData(List<T> content, int currentNumber, int beginIndex, int endIndex, Long total) {
        this.content = content;
        this.currentNumber = currentNumber;
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
        this.total = total;
    }

    public static <T> PageData<T> getDataFromPage(Page<T> page) {
        int currentIndex = page.getNumber() + 1;
        int beginIndex = Math.max(1, currentIndex - 5);
        int endIndex = Math.min(beginIndex + 10, page.getTotalPages());
        endIndex = Math.max(1, endIndex);
        return new PageData<>(page.getContent(), currentIndex, beginIndex, endIndex, page.getTotalElements());
    }

    public static <T> Page<T> getPageFromList(List<T> list, Pageable pageRequest,Long total) {
        int x = pageRequest.getPageNumber() * pageRequest.getPageSize();
        int y = Math.min(x+pageRequest.getPageSize(),list.size());
        List<T> subList = x < list.size() && !list.isEmpty()?
                list.subList(x,y) : new ArrayList<>();
        return new PageImpl(subList,pageRequest,total);
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
