package com.saberoueslati.devbuddy.domain.model

import androidx.compose.ui.graphics.Color
import java.util.Date

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val priority: Priority,
    val status: TaskStatus,
    val tags: List<TaskTag>,
    val dueDate: Date,
    val estimateHours: Int,
    val hasCodeSnippet: Boolean = false
)

enum class Priority(val displayName: String, val color: Color) {
    CRITICAL("Critical", Color(0xFFEF4444)),
    HIGH("High", Color(0xFFF97316)),
    MEDIUM("Medium", Color(0xFFF59E0B)),
    LOW("Low", Color(0xFF10B981))
}

enum class TaskStatus(val displayName: String, val color: Color) {
    TODO("Todo", Color(0xFF6B7280)),
    IN_PROGRESS("In Progress", Color(0xFF3B82F6)),
    BLOCKED("Blocked", Color(0xFFEF4444)),
    COMPLETED("Completed", Color(0xFF10B981))
}

enum class TaskTag(val displayName: String, val color: Color, val backgroundColor: Color) {
    BUG_FIX("Bug Fix", Color(0xFFFCA5A5), Color(0xFF7F1D1D)),
    FEATURE("Feature", Color(0xFF93C5FD), Color(0xFF1E3A8A)),
    CODE_REVIEW("Code Review", Color(0xFFC4B5FD), Color(0xFF581C87)),
    DOCUMENTATION("Documentation", Color(0xFFFDE68A), Color(0xFF92400E)),
    PERFORMANCE("Performance", Color(0xFFA7F3D0), Color(0xFF14532D)),
    AUTHENTICATION("Authentication", Color(0xFFA5B4FC), Color(0xFF312E81)),
    UI_UX("UI/UX", Color(0xFFF9A8D4), Color(0xFF831843)),
    PAYMENT("Payment", Color(0xFF67E8F9), Color(0xFF164E63)),
    API("API", Color(0xFF5EEAD4), Color(0xFF134E4A)),
    DATABASE("Database", Color(0xFFFED7AA), Color(0xFF9A3412))
}