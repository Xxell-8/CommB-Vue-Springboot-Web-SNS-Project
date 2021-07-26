package com.ssafy.commb.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Keyword")
@Getter
@Setter
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String keyword;

    /* 유저키워드 */
    @OneToMany(mappedBy = "keyword")
    private List<temp.UserKeyword> userKeywords = new ArrayList<temp.UserKeyword>();

    @OneToMany(mappedBy = "keyword")
    private List<DailyEvent> dailyEvents = new ArrayList<DailyEvent>();
}
