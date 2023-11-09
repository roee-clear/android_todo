package com.example.todoapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.navigation.enums.TodoScreen
import com.example.todoapp.ui.FormScreen
import com.example.todoapp.ui.NotesScreen
import com.example.todoapp.ui.TodoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoApp(
    viewModel: TodoViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = TodoScreen.valueOf(
        backStackEntry?.destination?.route ?: TodoScreen.Start.name
    )

    Scaffold(
        topBar = {
            TodoAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = TodoScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = TodoScreen.Start.name) {
                NotesScreen(
                    navigateToNoteFormScreen = { navController.navigate(TodoScreen.Form.name) },
                    viewModel,
                    Modifier
                )
            }

            composable(route = TodoScreen.Form.name) {
                FormScreen(
                    note = uiState.selectedNote,
                    onSubmitPress = {
                        viewModel.selectNote()

                        navController.popBackStack()
                    },
                    viewModel,
                    modifier = Modifier
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoAppBar(
    currentScreen: TodoScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.navigation_back_button)
                    )
                }
            }
        }
    )
}