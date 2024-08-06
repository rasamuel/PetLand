document.addEventListener('DOMContentLoaded', function () {
    var questions = document.querySelectorAll('.faq-question');
    questions.forEach(function (question) {
      question.addEventListener('click', function () {
        var answer = this.nextElementSibling;
        answer.style.display = answer.style.display === 'block' ? 'none' : 'block';
      });
    });
  });
  
document.getElementById('contactForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Evita el envío del formulario

    // Obtiene los valores del formulario
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const message = document.getElementById('message').value;

    // Valida los campos
    if (!name || !email || !message) {
        displayResponseMessage('Por favor, complete todos los campos.');
        return;
    }

    // Muestra un mensaje de éxito
    displayResponseMessage('¡Mensaje enviado con éxito!');

    // Limpia el formulario
    document.getElementById('contactForm').reset();
});

function displayResponseMessage(message) {
    const responseMessageElement = document.getElementById('responseMessage');
    responseMessageElement.textContent = message;
}

document.addEventListener('DOMContentLoaded', function () {
  // Funcionalidad FAQ existente
  var questions = document.querySelectorAll('.faq-question');
  questions.forEach(function (question) {
      question.addEventListener('click', function () {
          var answer = this.nextElementSibling;
          answer.style.display = answer.style.display === 'block' ? 'none' : 'block';
      });
  });

  // Funcionalidad del formulario de contacto existente
  document.getElementById('contactForm').addEventListener('submit', function(event) {
      event.preventDefault(); // Evita el envío del formulario

      // Obtiene los valores del formulario
      const name = document.getElementById('name').value;
      const email = document.getElementById('email').value;
      const message = document.getElementById('message').value;

      // Valida los campos
      if (!name || !email || !message) {
          displayResponseMessage('Por favor, complete todos los campos.');
          return;
      }

      // Muestra un mensaje de éxito
      displayResponseMessage('¡Mensaje enviado con éxito!');

      // Limpia el formulario
      document.getElementById('contactForm').reset();
  });

  function displayResponseMessage(message) {
      const responseMessageElement = document.getElementById('responseMessage');
      responseMessageElement.textContent = message;
  }

  // Nueva funcionalidad para la sección "Nuestros Servicios"
  const menuItems = document.querySelectorAll('.menu-item');
  const description = document.getElementById('service-description');

  const serviceDescriptions = {
      laboratorio: 'Estudios de laboratorio.',
      cirugias: 'Realizamos cirugías de alta complejidad con los mejores especialistas.',
      cardiologia: 'Servicios de cardiología con tecnología avanzada.',
      'planes-nutricionales': 'Planes nutricionales personalizados para cada mascota.'
  };

  menuItems.forEach(item => {
      item.addEventListener('click', function() {
          menuItems.forEach(i => i.classList.remove('active'));
          this.classList.add('active');
          description.textContent = serviceDescriptions[this.id];
      });
  });
});

