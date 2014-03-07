package com.ephron.sngtab.linkedin;

import java.util.EnumSet;

import com.ephron.sngtab.R;
import com.google.code.linkedinapi.client.enumeration.ProfileField;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.schema.DateOfBirth;
import com.google.code.linkedinapi.schema.Person;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.rtp.RtpStream;
import android.util.Log;
import android.widget.Toast;

public class LoginLinkedin {

	private ConfigureLinkedinAuthTokens cLT;
	private Context context;

	public LoginLinkedin(ConfigureLinkedinAuthTokens cLT, Context context) {
		this.cLT = cLT;
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	public void loginToLinkedin() {
		cLT.setLiToken(cLT.getoAuthService().getOAuthRequestToken(
				ConfigureLinkedinAuthTokens.OAUTH_CALLBACK_URL));
		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(cLT.getLiToken()
				.getAuthorizationUrl()));
		context.startActivity(i);
	}

	public String getAccessTokenTwitter(Uri uri) {
		// TODO Auto-generated method stub
		if (uri.getQueryParameter("oauth_verifier") != null) {
			String verifier = uri.getQueryParameter(
					"oauth_verifier");
			System.out.println("token" + verifier);
			
			
			
			
			LinkedInAccessToken accessToken = cLT.getoAuthService()
					.getOAuthAccessToken(cLT.getLiToken(), verifier);
			if (cLT.getAccessToken() == null) {
				Log.i("new access token", "");
				cLT.setAccessToken(accessToken);
			}

			Log.e("create access token", accessToken.getToken());
			cLT.setClient(cLT.getFactory().createLinkedInApiClient(
					accessToken));
			// cLT.getClient().postNetworkUpdate("LinkedIn Android app test");
			// Person profile = client.getProfileForCurrentUser();
			Person profile = null;
			try {

				profile = cLT.getClient().getProfileForCurrentUser(
						EnumSet.of(ProfileField.ID,
								ProfileField.FIRST_NAME,
								ProfileField.EMAIL_ADDRESS,
								ProfileField.LAST_NAME,
								ProfileField.HEADLINE,
								ProfileField.INDUSTRY,
								ProfileField.PICTURE_URL,
								ProfileField.DATE_OF_BIRTH,
								ProfileField.LOCATION_NAME,
								ProfileField.MAIN_ADDRESS,
								ProfileField.LOCATION_COUNTRY));
				//
				Toast.makeText(
						context,
						"Connection Established " + profile.getFirstName()
								+ " (" + profile.getEmailAddress() + "",
						Toast.LENGTH_LONG).show();
				Log.e("create access token secret", cLT.getClient()
						.getAccessToken().getTokenSecret());

			} catch (NullPointerException e) {
				// TODO: handle exception
			}

			/*
			 * * post.setOnClickListener(new OnClickListener() {
			 * 
			 * @Override public void onClick(View v) { // TODO
			 * Auto-generated method stub
			 * 
			 * // String status = currentStatus.getText().toString(); //
			 * client.updateCurrentStatus(status); client.postShare(
			 * "commentTEXT", "title", "description",
			 * "http://androidnews.co.in/wp-content/uploads/2013/11/Android-KitKat-4.4.jpg"
			 * ,
			 * "https://lh6.googleusercontent.com/-TET-Db529fE/AAAAAAAAAAI/AAAAAAAAATY/DWZ278Gh4Z0/photo.jpg"
			 * , visibility); // currentStatus.setText(""); } });
			 */

			// for update status......
			//
			try {
				// Person profile = client.getProfileById(id);
				Log.e("Name:",
						"" + profile.getFirstName() + " "
								+ profile.getLastName());
				DateOfBirth dateOfBirth = profile.getDateOfBirth();
				System.out.println(dateOfBirth);
				Log.e("BirthDate:", "" + dateOfBirth.toString());
				Log.e("Headline:", "" + profile.getHeadline());
				Log.e("email-id:", "" + profile.getEmailAddress());
				Log.e("Industry:", "" + profile.getIndustry());
				Log.e("Picture:", "" + profile.getPictureUrl());
			} catch (NullPointerException e) {
				// TODO: handle exception....
				Log.e("email-id:", "" + profile.getEmailAddress());
				System.out.println("null " + e.getMessage());
			}
			// Log.e("Picture:", "" + profile.getApiStandardProfileRequest()
			// + "\n"
			// + profile.getSiteStandardProfileRequest().getUrl());

			// signin.setText(profile.getFirstName() + " "
			// +profile.getLastName());

			/**
			 * Groups categoryImpl = client.getSuggestedGroups(); for (Group
			 * p : categoryImpl.getGroupList()) { Log.e("Name", "" +
			 * p.getName()); Log.e("Description", "" + p.getDescription());
			 * Log.e("      ", "" + "*****************"); }
			 * 
			 * 
			 * GroupMemberships member = client.getGroupMemberships(); for
			 * (GroupMembership p : member.getGroupMembershipList()) {
			 * Log.e("Name", "" + p.getPerson()); Log.e("Name", "" +
			 * p.getContactEmail()); Log.e("Name", "" + p.getGroup()); //
			 * Log.e("Description",""+p.getDescription()); Log.e("      ",
			 * "" + "*****************"); }
			 * 
			 * Companies companies = client.getFollowedCompanies();
			 * Log.e("Comny list", "" + companies); Log.e("Comny list", "" +
			 * companies.getCompanyList()); for (Company p :
			 * companies.getCompanyList()) { Log.e("Comny list", "" +
			 * companies.getCompanyList()); Log.e("Comny Name", "" +
			 * p.getName()); Log.e("Description", "" + p.getDescription());
			 * Log.e("      ", "" + "*****************"); }
			 */

			// Connections connections =
			// client.getConnectionsForCurrentUser();

			/*
			 * * for (Person p : connections.getPersonList()) {
			 * Log.e("Name", "" + p.getLastName() + " " + p.getFirstName());
			 * Log.e("Industry      ", "" + p.getIndustry());
			 * Log.e("      ", "" + "*****************");
			 * Log.e("getPhoneNumbers ", "" + p.getPhoneNumbers());
			 * Log.e("getMainAddress ", "" + p.getMainAddress());
			 * Log.e("getDateOfBirth ", "" + p.getDateOfBirth());
			 * Log.e("currentStatus ", "" + p.getCurrentStatus());
			 * Log.e("link          ", "" + p.getPublicProfileUrl());
			 * Log.e("position      ", "" + p.getEducations()); }
			 */
			return accessToken.getToken();
		}
		return null;
	}

}
