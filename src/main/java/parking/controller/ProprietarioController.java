package parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import parking.domain.Proprietario;
import parking.repository.ProprietarioRepository;

/**
 * 
 * @author gustavojotz
 *
 */
@RestController
@RequestMapping(path = "/proprietario")
public class ProprietarioController {

	@Autowired
	private ProprietarioRepository proprietarioRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("@proprietarioSecurityEvaluator.isOwner(#proprietario, principal)")
	public Proprietario update(@RequestBody(required = true) Proprietario proprietario) {
		return proprietarioRepository.save(proprietario);
	}

	@RequestMapping(method = RequestMethod.GET)
	public Proprietario get(@AuthenticationPrincipal UserDetails principal) {
		return proprietarioRepository.findByUsuario(principal.getUsername());
	}
}
