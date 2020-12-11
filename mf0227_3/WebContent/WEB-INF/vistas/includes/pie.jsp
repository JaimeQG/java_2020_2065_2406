
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
</main>
<footer>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">&copy; 2020 JQ</a>
		<div class="navbar-collapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="#">Privacidad</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Cookies</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Dónde
						estamos</a></li>
			</ul>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="#"><i class="fab fa-instagram"></i></a></li>
				<li class="nav-item"><a class="nav-link" href="#"><i class="fab fa-facebook"></i></a></li>
				<li class="nav-item"><a class="nav-link" href="#"><i class="fab fa-twitter"></i></a></li>
				<li class="nav-item"><a class="nav-link" href="#"><i class="fab fa-whatsapp"></i></a></li>
				<li class="nav-item"><a class="nav-link" href="#"><i class="fas fa-envelope-square"></i></a></li>
			</ul>
		</div>
	</nav>
</footer>

<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/bs-custom-file-input.min.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/dataTables.bootstrap4.min.js"></script>

<script>
      bsCustomFileInput.init();
</script>

<script>
	$(document).ready(function() {
		$('table').DataTable({
			"language" : {
				"url" : "js/Spanish.json"
			}
		});
	});
</script>

<script>
	// Example starter JavaScript for disabling form submissions if there are invalid fields
	(function() {
		'use strict';
		window.addEventListener('load', function() {
			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.getElementsByClassName('needs-validation');
			// Loop over them and prevent submission
			var validation = Array.prototype.filter.call(forms, function(form) {
				form.addEventListener('submit', function(event) {
					if (form.checkValidity() === false) {
						event.preventDefault();
						event.stopPropagation();
					}
					form.classList.add('was-validated');
				}, false);
			});
		}, false);
	})();
</script>


</body>
</html>