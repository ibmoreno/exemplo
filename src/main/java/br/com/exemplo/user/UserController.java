package br.com.exemplo.user;


import br.com.exemplo.exception.NotFoundException;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.com.exemplo.util.JsonPatchMapper.applyPatch;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(userService.getAll(pageable));
    }

    @GetMapping(path = "/{code}")
    public ResponseEntity<UserDTO> getById(@PathVariable Integer code) {
        return userService.getById(code).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.save(userDTO));
    }

    @PutMapping(path = "/{code}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update(@PathVariable Integer code, @Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.update(userDTO));
    }

    @PatchMapping(path = "/{code}", consumes = "application/json-patch+json")
    public ResponseEntity<UserDTO> update(@PathVariable Integer code, @Valid @RequestBody JsonPatch jsonPatch) {
        UserDTO userDTO = userService.getById(code).orElseThrow(NotFoundException::new);
        userDTO = applyPatch(jsonPatch, userDTO);
        userDTO = userService.update(userDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PatchMapping(path = "/{code}/credential", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCredential(@PathVariable Integer code, @Valid @RequestBody CredentialDTO credentialDTO) {
        userService.saveCredential(code, credentialDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{code}")
    public ResponseEntity<Void> delete(@PathVariable Integer code) {
        userService.delete(code);
        return ResponseEntity.noContent().build();
    }

}