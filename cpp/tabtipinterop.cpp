#include <jni.h>
#include "tabtipinterop.h"
#include <Windows.h>

HWND tabTipWindow = NULL;

void closeTabTip() {
    const wchar_t* keyboardClassName = L"IPTIP_Main_Window";
    UINT wmSysCommand = 0x0112;
    WPARAM scClose = WPARAM(0xF060);

    HWND handle = FindWindow(keyboardClassName, NULL);
    if (handle != NULL) {
        SendMessage(handle, wmSysCommand, scClose, LPARAM(0));
    }
}

void openTabTip() {
    // Close the TabTip.exe program if it was earlier open
    closeTabTip();
    // Path to the TabTip.exe program
    const wchar_t* tabTipPath = L"C:\\Program Files\\Common Files\\Microsoft Shared\\ink\\TabTip.exe";

    // Use ShellExecute to open TabTip.exe
    HINSTANCE result = ShellExecute(NULL, L"open", tabTipPath, NULL, NULL, SW_SHOWNORMAL);

    // Check for errors
    if ((intptr_t)result <= 32) {
        // Handle error (show an error message)
        MessageBox(NULL, L"Error opening TabTip.exe", L"Error", MB_ICONERROR | MB_OK);
    }
}

JNIEXPORT void JNICALL Java_TabTipInterop_openTabTip(JNIEnv* env, jclass jclazz) {
    openTabTip();
}

JNIEXPORT void JNICALL Java_TabTipInterop_closeTabTip(JNIEnv* env, jclass jclazz) {
    closeTabTip();
}
