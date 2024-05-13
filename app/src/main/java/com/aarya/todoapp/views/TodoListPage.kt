package com.aarya.todoapp.views

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aarya.todoapp.R
import com.aarya.todoapp.model.data.Todo
import com.aarya.todoapp.ui.theme.grey
import com.aarya.todoapp.ui.theme.subtleWhite
import com.aarya.todoapp.viewmodel.TodoViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoListPage(context : Context , viewModel: TodoViewModel) {

    val snackbarHostState = remember { SnackbarHostState() }
    val todoList by viewModel.todoList.observeAsState()
    var inputText by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .padding(
                start = 16.dp,
                top = 4.dp,
                end = 16.dp,
                bottom = 12.dp
            )
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
            ,
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            OutlinedTextField(
                modifier = Modifier.weight(1f)
                    .padding(end=8.dp),
                label = { Text("Add Todos") },
                value = inputText,
                onValueChange ={
                    inputText = it
                }
            )
            Button (
                onClick = {
                    val trimmedInput  = inputText.trim()
                    if(trimmedInput.isNotBlank()){
                        viewModel.addTodo(trimmedInput)
                        inputText = ""
                    }else{
                         Toast.makeText(context, "Please Enter a Valid Todo", Toast.LENGTH_SHORT).show()
                    }

                }
            ){
                Text (text = "Add")
            }
        }
        todoList?.let{
            LazyColumn (
                content = {
                    itemsIndexed(it){index: Int , item: Todo ->
                        TodoItem(item = item, onDelete = {
                            viewModel.deleteTodo(item.id)
                        })
                    }
                }
            )
        }?: Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "No Current Todo's",
                fontSize = 16.sp
            )



    }
}

@Composable
fun TodoItem(item: Todo, onDelete : ()-> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(grey)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically

    ){
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = SimpleDateFormat("MM-dd HH:mm", Locale.ENGLISH).format(item.createdAt),
                fontSize = 12.sp,
                color = Color.Gray
            )
            Text(text = item.title)
        }
        IconButton(onClick= onDelete){
            Icon(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Delete",
                Modifier.size(24.dp)
            )
        }
    }
}
