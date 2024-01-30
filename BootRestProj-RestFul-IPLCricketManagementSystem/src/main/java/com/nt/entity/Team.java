//Team.java(Parent class)

package com.nt.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="JPA_OTM_TEAM1")
@Data
@RequiredArgsConstructor
public class Team implements Serializable {
	@Id
	@GeneratedValue
	private Integer tid;
	@Column(length = 20)
	@NonNull
	private String name;
	@Column(length = 20)
	@NonNull
	private String city;
	@Column(length = 20)
	@NonNull
	private String owner;
	@Column(length = 20)
	@NonNull
	private String captain;
	
	
	@OneToMany(targetEntity = Player.class,cascade = CascadeType.ALL,
			  fetch = FetchType.LAZY )
	@JoinColumn(name="TEAM_ID",referencedColumnName = "TID")//FK Column
	private List<Player> playerDetails;
	
	
	
	public Team() {
		System.out.println("Team::0-param constructor");
	}

}
