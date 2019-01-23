package com.stackroute;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication
public class MuzixApplication implements ApplicationListener<ContextRefreshedEvent> , CommandLineRunner
{
@Autowired
	TrackRepository trackRepository;
	public static void main(String[] args)
	{
		SpringApplication.run(MuzixApplication.class, args);
	}

	public void run(String...args) throws Exception {
		Track track=new Track(1,"default","good");
		trackRepository.save(track);
	}
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Track track=new Track(1,"default","good");
		trackRepository.save(track);
	}
}

