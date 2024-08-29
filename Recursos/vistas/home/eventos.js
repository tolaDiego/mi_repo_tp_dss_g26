document.querySelectorAll(".nav-link").forEach((boton_navbar) => {
        boton_navbar.addEventListener("click", (evento_click) => {

        })
    }
)

function vistaRegistroColaborador() {

    return `<div class="wave-background">

   <div id="page-content">
    <header class="header-form-registro-colaborador">
        <h1 class="title">Registro de Colaboradores</h1>
        <p class="subtitle">Únete a nuestra misión de ayudar a personas en situación vulnerable.</p>
    </header>

    <form class="registration-form">
        <div class="form-group">
            <label for="first-name" class="form-label">Nombre</label>
            <input type="text" id="first-name" name="first-name" class="form-input" required>
        </div>

        <div class="form-group">
            <label for="last-name" class="form-label">Apellido</label>
            <input type="text" id="last-name" name="last-name" class="form-input" required>
        </div>

        <fieldset class="form-group contact-group">
            <legend>Medios de Contacto (al menos uno requerido)</legend>

            <label for="email" class="form-label">Correo Electrónico</label>
            <input type="email" id="email" name="email" class="form-input">

            <label for="phone" class="form-label">Teléfono</label>
            <input type="tel" id="phone" name="phone" class="form-input">

            <label for="whatsapp" class="form-label">WhatsApp</label>
            <input type="tel" id="whatsapp" name="whatsapp" class="form-input">
        </fieldset>

        <fieldset class="form-group optional-info">
            <legend>Información Opcional</legend>

            <label for="dob" class="form-label">Fecha de Nacimiento</label>
            <input type="date" id="dob" name="dob" class="form-input">

            <label for="address" class="form-label">Dirección</label>
            <input type="text" id="address" name="address" class="form-input">
        </fieldset>

        <div class="form-group">
            <label for="collaboration-type" class="form-label">¿Cómo puedes colaborar?</label>
            <select id="collaboration-type" name="collaboration-type" class="form-input" required>
                <option value="donating-money">Donando dinero</option>
                <option value="distributing-meals">Distribuyendo viandas</option>
                <option value="donating-meals">Donando viandas</option>
            </select>
        </div>
        <div class="form-group">
            <label for="first-name" class="form-label">Contraseña</label>
            <input type="password"  name="first-name" class="form-input" required>
            <label for="first-name" class="form-label">Repetir Contraseña</label>
            <input type="password"  name="first-name" class="form-input" required>
        </div>

        <div class="form-group">
            <button type="submit" class="submit-button">Registrarse</button>
        </div>
    </form>
</div>
</div>
    `

}

function vistaOpcionesPerfil() {
    return `
    <ul class="menu-perfil">
        <li class="dropdown">
            <a href="#" class="dropbtn">Mi Cuenta</a>
            <ul class="dropdown-content">
                <li><a href="#">Datos Personales</a></li>
                <li><a href="#">Cerrar Sesion</a></li>
            </ul>
        </li>
    </ul>

`
}

document.getElementById("boton-registro").addEventListener("click", (evento_click) => {
    document.getElementById("main").innerHTML = "";
    document.getElementById("main").innerHTML = vistaRegistroColaborador()

})

function vistaFormSesion() {
    return `  <div class="wave-background">
        <div id="login-page-content">
    <header class="login-header">
        <h1 class="login-title">Iniciar Sesión</h1>
        <p class="login-subtitle">Accede a tu cuenta de colaborador</p>
    </header>

    <form class="login-form">
        <div class="login-form-group">
            <label for="login-email" class="login-form-label">Correo Electrónico</label>
            <input type="email" id="login-email" name="email" class="login-form-input" required>
        </div>

        <div class="login-form-group">
            <label for="login-password" class="login-form-label">Contraseña</label>
            <input type="password" id="login-password" name="password" class="login-form-input" required>
        </div>

        <div class="login-form-group">
            <button type="submit" class="login-submit-button" id="login-submit-button">Iniciar Sesión</button>
        </div>
    </form>
</div>
</div>
    `
}

document.getElementById("boton-sesion").addEventListener("click", (evento_click) => {
    document.getElementById("main").innerHTML = "";
    document.getElementById("main").innerHTML = vistaFormSesion()

})


function vistaCargaArchivo() {
    return `

    <div class="wave-background">
        <div id="page-content">
        <h1 >Cargar Archivo CSV</h1>
            <form action="/upload" class="csv-upload-form" method="post" enctype="multipart/form-data" >
             <label for="csvFile" class="form-label ">Seleccione el archivo CSV:</label>
             <input type="file" id="csvFile" name="csvFile" accept=".csv" class="form-input carga-csv-form-input" required>
             <button type="submit"class="login-submit-button" id="login-submit-button">Cargar Archivo</button>
            </form>
        </div>
    </div>
    `
}

document.getElementById("boton-cargar-archivo-csv").addEventListener("click", (evento_click) => {
    document.getElementById("main").innerHTML = "";
    document.getElementById("main").innerHTML = vistaCargaArchivo()
})


function vistaReporteFalla() {
    return `
<div class="wave-background">
  <div class="incident-report-container" >
    <div class="report-header">
      <h1>Reportar Falla en Heladera</h1>
    </div>

    <div class="report-card">
      <div class="report-section">
        <label for="reporter" class="report-label">Colaborador que reporta:</label>
        <input type="text" id="reporter" name="reporter" class="report-input" placeholder="Nombre del colaborador" required>
      </div>

      <div class="report-section">
        <label for="fridge" class="report-label">Heladera afectada:</label>
        <select id="fridge" name="fridge" class="report-input" required>
          <option value="" disabled selected>Seleccione la heladera</option>
          <option value="heladera1">Heladera 1</option>
          <option value="heladera2">Heladera 2</option>
          <option value="heladera3">Heladera 3</option>
        </select>
      </div>

      <div class="report-section">
        <label for="type" class="report-label">Tipo de falla:</label>
        <select id="type" name="type" class="report-input" required>
          <option value="" disabled selected>Seleccione el tipo de falla</option>
          <option value="mecanica">Mecánica</option>
          <option value="electrica">Eléctrica</option>
          <option value="refrigeracion">Refrigeración</option>
          <option value="otra">Otra</option>
        </select>
      </div>

      <div class="report-section">
        <label for="description" class="report-label">Descripción:</label>
        <textarea id="description" name="description" class="report-input" placeholder="Describa la falla" rows="4"></textarea>
      </div>

      <div class="report-section">
        <label for="photo" class="report-label">Subir foto:</label>
        <input type="file" id="photo" name="photo" class="report-input" accept="image/*">
      </div>

      <div class="report-section">
        <label for="incident_datetime" class="report-label">Fecha y hora del incidente:</label>
        <input type="datetime-local" id="incident_datetime" name="incident_datetime" class="report-input" required>
      </div>

      <button type="submit" class="submit-button">Enviar Reporte</button>
    </div>
  </div>
</div>
     `
}

document.getElementById("boton-reporte-falla").addEventListener("click", (evento_click) => {
    document.getElementById("main").innerHTML = "";
    document.getElementById("main").innerHTML = vistaReporteFalla()
})

function vistaHeladeras() {
    return `
    <div class=" wave-background" >
        <h2>Reporte de Heladeras</h2>

        <table>
            <thead>
            <tr>
            <th>Numero</th>
                <th>Dirección</th>
                <th>Cantidad de Viandas</th>
                <th>Fallas</th>
            </tr>
            </thead>
            <tbody>
            <tr>
            <td>1</td>
                <td>Calle Falsa 123</td>
                <td>50</td>
                <td>
                    <span class="toggle-details">Ver Fallas</span>
                    <div class="details">
                        <dl>
                            <dt>Descripción:</dt>
                            <dd>Puerta no cierra bien</dd>
                            <dt>Tipo de Falla:</dt>
                            <dd>Mecánica</dd>
                            <dt>Fecha de Reporte:</dt>
                            <dd>12/08/2024</dd>
                            <dt>Arreglado:</dt>
                            <dd>No</dd>
                        </dl>
                        <hr/>
                        <dl>
                            <dt>Descripción:</dt>
                            <dd>Termostato roto</dd>
                            <dt>Tipo de Falla:</dt>
                            <dd>Eléctrica</dd>
                            <dt>Fecha de Reporte:</dt>
                            <dd>10/08/2024</dd>
                            <dt>Arreglado:</dt>
                            <dd>Sí</dd>
                        </dl>
                    </div>
                </td>
            </tr>     <tr>
            <td>2</td>
                <td>Calle Falsa 123</td>
                <td>50</td>
                <td>
                    <span class="toggle-details">Ver Fallas</span>
                    <div class="details">
                        <dl>
                            <dt>Descripción:</dt>
                            <dd>Puerta no cierra bien</dd>
                            <dt>Tipo de Falla:</dt>
                            <dd>Mecánica</dd>
                            <dt>Fecha de Reporte:</dt>
                            <dd>12/08/2024</dd>
                            <dt>Arreglado:</dt>
                            <dd>No</dd>
                        </dl>
                        <hr/>
                        <dl>
                            <dt>Descripción:</dt>
                            <dd>Termostato roto</dd>
                            <dt>Tipo de Falla:</dt>
                            <dd>Eléctrica</dd>
                            <dt>Fecha de Reporte:</dt>
                            <dd>10/08/2024</dd>
                            <dt>Arreglado:</dt>
                            <dd>Sí</dd>
                        </dl>
                    </div>
                </td>
            </tr>
            <!-- Más filas pueden ir aquí -->
            </tbody>
        </table>


    </div>
     `
}
document.getElementById("boton-tabla-heladera").addEventListener("click", (evento_click) => {
    console.log("se presiono:" + 3);
    document.getElementById("main").innerHTML = vistaHeladeras();

    // Ahora que el contenido está en el DOM, agregamos los eventos
    document.querySelectorAll(".toggle-details").forEach((toggle) => {
        console.log("se presiono detalles:" + toggle);
        toggle.addEventListener('click', function () {
            var details = this.nextElementSibling;
            details.style.display = details.style.display === 'block' ? 'none' : 'block';
        });
    });
});

function vistaDonarDinero() {
    return `
<div class="wave-background">
  <div class="DonarDinero-container" >
    <div class="DonarDinero-header">
      <h1>Donar dinero</h1>
    </div>

    <div class="DonarDinero-card">
      <div class="DonarDinero-section">
        <label for="DonarDinero" class="DonarDinero-label">Donar Dinero:</label>
        <input type="text" id="DonarDinero" name="DonarDinero" class="DonarDinero-input" placeholder="Importe" required>
      </div>
      <div class="DonarDinero-section">
        <label for="DonarDinero" class="DonarDinero-label">Fecha:</label>
        <input type="text" id="DonarDinero" name="DonarDinero" class="DonarDinero-input" placeholder="Fecha de donacion" required>
      </div>

      <button type="submit" class="submit-button">Donar</button>
    </div>
  </div>
</div>
     `
}
document.getElementById("donar-dinero-drop").addEventListener("click", (evento_click) => {
    document.getElementById("main").innerHTML = "";
    document.getElementById("main").innerHTML = vistaDonarDinero()
})

function vistaDonarVianda() {
    return `
<div class="wave-background">
  <div class="donar-vianda-container" >
    <div class="donar-vianda-header">
      <h1>Donar vianda</h1>
    </div>

    <div class="donar-vianda-card">
      <div class="donar-vianda-section">
        <label for="donar-vianda" class="donar-vianda-label">tipo:</label>
        <input type="text" id="donar-vianda" name="donar-vianda" class="donar-vianda-input" placeholder="Comida" required>
      </div>
      <div class="donar-vianda-section">
        <label for="donar-vianda" class="donar-vianda-label">Fecha de Caducidad:</label>
        <input type="datetime-local" id="donar-vianda" name="donar-vianda" class="donar-vianda-input" placeholder="Fecha de Caducidad" required>
      </div>
      <div class="donar-vianda-section">
        <label for="donar-vianda" class="donar-vianda-label">Fecha de Donacion:</label>
        <input type="datetime-local" id="donar-vianda" name="donar-vianda" class="donar-vianda-input" placeholder="Fecha de Donacion" required>
      </div>
      <div class="donar-vianda-section">
        <label for="donar-vianda" class="donar-vianda-label">Colaborador:</label>
        <input type="text" id="donar-vianda" name="donar-vianda" class="donar-vianda-input" placeholder="Nombre de Colaborador" required>
      </div>
      <div class="donar-vianda-section">
        <label for="donar-vianda" class="donar-vianda-label">Heladera:</label>
        <input type="text" id="donar-vianda" name="donar-vianda" class="donar-vianda-input" placeholder="Nombre de heladera" required>
      </div>
      <div class="donar-vianda-section">
        <label for="donar-vianda" class="donar-vianda-label">Calorias:</label>
        <input type="text" id="donar-vianda" name="donar-vianda" class="donar-vianda-input" placeholder="Calorias" required>
      </div>
      <div class="donar-vianda-section">
        <label for="donar-vianda" class="donar-vianda-label">Peso:</label>
        <input type="text" id="donar-vianda" name="donar-vianda" class="donar-vianda-input" placeholder="Peso" required>
      </div>

      <button type="submit" class="submit-button">Donar</button>
    </div>
  </div>
</div>
     `
}
document.getElementById("donar-vianda-drop").addEventListener("click", (evento_click) => {
    document.getElementById("main").innerHTML = "";
    document.getElementById("main").innerHTML = vistaDonarVianda()
})


function vistaDistribuirVianda() {
    return `
<div class="wave-background">
  <div class="DistribuirVianda-container" >
    <div class="DistribuirVianda-header">
      <h1>Donar vianda</h1>
    </div>

    <div class="DistribuirVianda-card">
      <div class="DistribuirVianda-section">
        <label for="DistribuirVianda" class="DistribuirVianda-label">Heladera inicial:</label>
        <input type="text" id="DistribuirVianda" name="DistribuirVianda" class="DistribuirVianda-input" placeholder="Heladera" required>
      </div>
      <div class="DistribuirVianda-section">
        <label for="DistribuirVianda" class="DistribuirVianda-label">Heladera final:</label>
        <input type="text" id="DistribuirVianda" name="DistribuirVianda" class="DistribuirVianda-input" placeholder="Heladera" required>
      </div>
      <div class="DistribuirVianda-section">
        <label for="DistribuirVianda" class="DistribuirVianda-label">Fecha de Donacion:</label>
        <input type="datetime-local" id="DistribuirVianda" name="DistribuirVianda" class="DistribuirVianda-input" placeholder="Fecha de Donacion" required>
      </div>

      <button type="submit" class="submit-button">Donar</button>
    </div>
  </div>
</div>
     `
}
document.getElementById("DistribuirVianda-drop").addEventListener("click", (evento_click) => {
    document.getElementById("main").innerHTML = "";
    document.getElementById("main").innerHTML = vistaDistribuirVianda()
})




