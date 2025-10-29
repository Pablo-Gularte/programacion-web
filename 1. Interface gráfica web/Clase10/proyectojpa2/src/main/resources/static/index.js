//Variables Globales
const tablaProductosHTML = document.getElementById('tablaProductos')
const nombre =  document.getElementById('nombre')
const precio =  document.getElementById('precio')
const cantidad =  document.getElementById('cantidad')
const btnGuardar =  document.getElementById('btnGuardar')
let tituloModal = document.getElementById('tituloModal')
let idProductoModificar = 0

//Guardar Producto
btnGuardar.addEventListener('click',()=>{
    if(tituloModal.innerText === "Modificar Producto"){
        crearProducto(idProductoModificar)
    }
    else if(tituloModal.innerText === "Crear Producto"){
        crearProducto(0)
    }
})

//Eliminar Producto
async function eliminarProducto(id){
    // await fetch('http://localhost:8080/api/producto/delete/'+id,{
    //     method: 'DELETE',
    //     mode: 'cors', 
    //     headers: {
    //       'Content-Type': 'application/json'
    //     },
        
    //   })
    await axios.delete('http://localhost:8080/api/producto/delete/' + id, {
      method: 'DELETE',
      mode: 'cors',
      headers: {
          'Content-Type': 'application/json'
      }
  })
  
      .then((res)=>{
        console.log(res);
        alert("Producto eliminado")
        obtenerProductos()
      })
      .catch((err)=>{
        console.log(err);
        alert("Error en la eliminacion del producto")
      })
}


//Modificar Producto
function modificarProducto(id){
    tituloModal.innerText = "Modificar Producto"
    modal.style.display = "block";//muestra el modal
    
    idProductoModificar = id //Esta variable se usa para enviar el ID correcto al servidor luego de los cambios
    
    resetModal() //limpia los campos del formulario.

    //Filtra la lista de productos (listaProductos) buscando el producto cuya propiedad idproducto coincida con el id pasado como parámetro.Devuelve un array con el producto encontrado o un array vacío si no se encuentra el producto.
    let producto = listaProductos.filter(producto => producto.idproducto === id)
    
    console.log(producto[0]);
    
    //Establece el valor del campo de nombre en el formulario con el nombre del producto encontrado.
    //Accede al primer elemento del array resultante (producto[0]) y obtiene su propiedad nombreproducto.
    nombre.value = producto[0].nombreproducto

    precio.value = producto[0].precioproducto
    cantidad.value = producto[0].cantidadproducto


}

//Crear Producto
async function crearProducto(id){
    let productoGuardar = {
        idproducto:id,
        nombreproducto:nombre.value,
        precioproducto:parseInt(precio.value),
        cantidadproducto:parseInt(cantidad.value),
        
    }
    console.log(productoGuardar);

    await fetch('http://localhost:8080/api/producto/new',{
        method: 'POST',
        mode: 'cors', 
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(productoGuardar) 
      })
      .then((producto)=>{
        console.log(producto);
        alert("Producto guardado")
        modal.style.display = "none";
        obtenerProductos()
        resetModal()
      })
      .catch((err)=>{
        console.log(err);
        alert("Error en el alta del producto")
      })

   

}


//Consumo API
let listaProductos = []
async function obtenerProductos(){
    tablaProductosHTML.innerHTML = ''
    await fetch('http://localhost:8080/api/producto')
        .then(response =>  response.json())
        .then(productos => {
            console.log(productos)
            listaProductos = productos
            for(let producto of productos){
                
                tablaProductosHTML.innerHTML +=
                '<tr>'+
                '    <th scope="row">'+producto.idproducto+'</th>'+
                '    <td>'+producto.nombreproducto+'</td>'+
                '    <td>'+producto.precioproducto+'</td>'+
                '    <td>'+producto.cantidadproducto+'</td>'+
                '    <td>'+
                '        <input type="button" class="btn btn-outline-danger" value="Eliminar" onclick=eliminarProducto('+producto.idproducto+')>'+
                '        <input type="button" class="btn btn-outline-success" value="Modificar" onclick=modificarProducto('+producto.idproducto+')>'+
                '    </td>'+
                '</tr>'
            }
            
        })
        .catch((err)=>{
            console.log(err);
            alert("Error en el listado de productos")
        })
}
obtenerProductos()


//Reset Modal
function resetModal(){
    nombre.value = ""
    cantidad.value = ""
    precio.value = ""
}


//Modal 
var modal = document.getElementById("myModal");
var btn = document.getElementById("myBtn");

//Busca el primer elemento de clase "close" en el documento.
//Esto suele ser un icono de cierre para cerrar el modal.
var span = document.getElementsByClassName("close")[0];

//Asigna una función al evento de clic del botón "myBtn".
//Cuando se presiona este botón, cambia el texto del título del modal a "Crear Producto" y muestra el modal.
btn.onclick = function() {
    tituloModal.innerText = "Crear Producto"
    modal.style.display = "block";
}

//Cuando se presiona este elemento (generalmente un icono de cierre), oculta el modal y llama a resetModal().
span.onclick = function() {
  modal.style.display = "none";
  resetModal()
}

//Si el objetivo del clic es el modal mismo, ocultará el modal y llamará a resetModal().
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
    resetModal()
  }
}