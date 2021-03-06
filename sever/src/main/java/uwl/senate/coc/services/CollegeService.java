package uwl.senate.coc.services;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uwl.senate.coc.entities.College;
import uwl.senate.coc.repositories.CollegeRepository;

@Service
public class CollegeService {
	
	@Autowired
	private CollegeRepository collegeRepo;

	public College update(Long pathId, College c) {
		if( pathId == null || c == null || pathId != c.getId() ) {
			throw new IllegalArgumentException();
		}
		
		Boolean exists = this.collegeRepo.findById( c.getId() ).isPresent();		
		if( exists ) { 
			College temp = this.collegeRepo.findById( c.getId() ).get();
			if( c.getYear() == null || !c.getYear().equals( temp.getYear() ) ) {
				throw new IllegalArgumentException();
			} else {
				return this.collegeRepo.save( c );
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	public List<College> findByYear(
			@Pattern(regexp = "\\b\\d{4}\\b", message = "the year format is wrong") String year ) {
		return this.collegeRepo.findByYear( year );
	}

	public College create(College college) {
		return this.collegeRepo.save( college );
	}

	public void delete(Long id) {
		this.collegeRepo.deleteById( id );
	}
	
	public College read( Long id ) {
		return this.collegeRepo.getOne( id );
	}
}
