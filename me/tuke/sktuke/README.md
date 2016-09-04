This Auto Updater uses a thread from SkUnity Forums to get and download the latest version of your addon.<br>
It isn't official from SkUnity and neither has any connection with them.

#How to use
First, place the class in your project.
Then, the usage of this are simple, you just need to make a new instance.
<p>
	<code>SkUnityUpdater updater = new SkUnityUpdater(instance, file, threadID);</code>
</p>
Where:<br>
<code>instance</code> - The instance of your plugin.<br>
<code>file</code> - The jar file of your plugin (You can get it with <code>instance.getFile()</code>). <br>
<code>threadID</code> - The ID of the thread of your addon, you can get it from the url:<br>
<code>https://forums.skunity.com/t/addon-tuske/7397/1</code> where the ID is <code>7397</code>
<p>Before check for latest update, you need to know some things:
	<ul>
		<li> When the plugin will check for the latest update, the class will search for first hyperlink <code>[Plugin's name].jar</code> and then
get the direct download link from it. But, if you use a different message as <code>Download here</code>, <code>Click here to download</code>or something, you will have to use the second constructor.<p>
<code>SkUnityUpdater updater = new SkUnityUpdater(instance, file, threadID, "Download here");</code></p>
</li>
<li>If you have a external link, you can use the method <code>useDirectURL(String)</code>
<p><code>updater.useDirectURL("http://yourSiteHere.com/download/latest");</code></p>
</li>
</ul>
#Update Checker
To check if there is a new update, you have to use this:
<p><code>updater.checkForUpdate();</code></p>
But, take a note: It may take a <b>bit longer to load your plugin</b> if you use in <code>onEnable()</code>. So, I recommend to you schedule a task later then check it out.<br>
Now it's done. If it has any new update, you can get this using like this:
<p><pre>if (!updater.isLatestVersion()){
	instance.getLogger().info("New update v" + updater.getLatestVersion() + " is available!");
	instance.getLogger().info("You can see what's new on SkUnity: " + updater.getThreadURL());
	instance.getLogger().info("Download: " + updater.getDownloadURL());
}
</pre></p>
#Auto Updater
After you had checked for the next update, you can download it like this:
<p><code>boolean result = updater.downloadLatest();</code></p>
It will download the latest version and will return true if it was successful. The download file will save in <code>plugins/[Plugin's folder]/[Plugin's name].jar</code>. If you want to automatically update the plugin, I recommend to make it in <code>onDisable()</code>. I don't know if it is safe, but I made a test with and works fine and no problems.
<pre>
@Override
public void onDisable() {
	if (updater.hasDownloadReady(true)){
		HandlerList.unregisterAll(this); //I recommend it to prevent some problems.
		updater.updatePlugin(true);
	}
}
</pre>
First, the <code>hasDownloadReady(true)</code> will check if there is a downloaded file in plugin's folder and the parameter <code>boolean</code> is if it will ignore if the plugin is already updated. If true, the plugin will update if has any file <code>[Plugin's name].jar</code> in plugin's folder.<br>
Second, the <code>updatePlugin(true)</code> will replace the current jar file in <code>/plugins</code> to the latest in <code>plugins/[Plugin's folder]</code> and the parameter <code>boolean</code> is if it will make a backup of current jar file to plugin's folder.
<p> That's it, plugin updated.


